package com.example.ktor_chat_app.core.presentation

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktor_chat_app.core.utility.DispatcherProvider
import com.example.ktor_chat_app.core.utility.credentials
import com.example.ktor_chat_app.screen_chat.domain.use_case.modify_chat_use_case.ModifyChatUseCases
import com.example.ktor_chat_app.screen_contact.domain.use_case.ContactUseCases
import com.example.ktor_chat_app.web_socket.data.remote.req_and_res.MessageDelivered
import com.example.ktor_chat_app.web_socket.data.remote.req_and_res.MessageSeen
import com.example.ktor_chat_app.web_socket.data.remote.request.ConnectToServer
import com.example.ktor_chat_app.web_socket.data.remote.responce.ChatMessage
import com.example.ktor_chat_app.web_socket.data.remote.responce.User
import com.example.ktor_chat_app.web_socket.data.remote.responce.dto.asDataBaseModel
import com.example.ktor_chat_app.web_socket.domain.use_case.web_socket_use_case.WebSocketUseCases
import com.tinder.scarlet.WebSocket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val webSocketUseCases: WebSocketUseCases,
    private val modifyChatUseCases: ModifyChatUseCases,
    private val contactUseCases: ContactUseCases,
    private val dataStore: DataStore<Preferences>
    ):ViewModel() {

    private var contactsUpdateJob: Job? = null
    private var chatUpdateJob: Job? = null

     //private
     fun observeBaseModels() {
        viewModelScope.launch(dispatchers.io) {
            webSocketUseCases.observeBaseModel().collect{ baseModel ->

                when (baseModel) {

                    is ChatMessage -> {

                        chatUpdateJob?.join()
                        chatUpdateJob = viewModelScope.launch(dispatchers.io) {
                            modifyChatUseCases.insertChat(
                                baseModel
                              )
                        }

                        viewModelScope.launch {
                            delay(500L)
                            contactUseCases.updateContactActiveStatus(baseModel.fromId)
                        }

                        viewModelScope.launch {
                            webSocketUseCases.sendMessageDeliveredUpdate(
                                messageId = baseModel.messageId,
                                fromId = baseModel.fromId
                            )
                        }
                    }

                    is MessageSeen -> {
                        chatUpdateJob?.join()
                        chatUpdateJob = viewModelScope.launch(dispatchers.io) {
                            modifyChatUseCases.messageSeenUpdate(
                                messageId = baseModel.messageId,
                            )
                        }
                    }

                    is MessageDelivered -> {
                            chatUpdateJob?.join()
                            chatUpdateJob = viewModelScope.launch(dispatchers.io) {
                                modifyChatUseCases.messageDeliveredUpdate(
                                    messageId = baseModel.messageId
                                )
                            }
                    }

                    is User -> {
                        contactsUpdateJob?.join()
                        contactsUpdateJob =
                            viewModelScope.launch(dispatchers.io){
                            contactUseCases.insertContactInDatabase(
                                baseModel.asDataBaseModel()
                            )
                        }
                    }
                }
            }
        }
    }

   // private
    fun observeConnectionEvent(){
        viewModelScope.launch(dispatchers.io) {
            webSocketUseCases.observeConnectionEvents().collect{ connectionEvent->
                when(connectionEvent){

                    is WebSocket.Event.OnConnectionOpened<*> -> {
                        webSocketUseCases.sendBaseModel(
                                ConnectToServer(
                                   name = dataStore.credentials()[1],
                                    id = dataStore.credentials()[0]
                                )
                            )
                    }

                    is WebSocket.Event.OnConnectionFailed -> {

                    }

                    is WebSocket.Event.OnConnectionClosed -> {

                    }
                    else -> Unit
                }
            }
        }
    }

    // init {
//        observeConnectionEvent()
//        observeBaseModels()
  // }
}
