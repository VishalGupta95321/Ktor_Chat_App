package com.example.ktor_chat_app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktor_chat_app.data.remote.dto.asDataBaseModel
import com.example.ktor_chat_app.data.remote.model.ChatMessage
import com.example.ktor_chat_app.data.remote.model.MessageDelivered
import com.example.ktor_chat_app.data.remote.model.MessageSeen
import com.example.ktor_chat_app.data.remote.model.User
import com.example.ktor_chat_app.web_socket.domain.use_case.web_socket_use_case.WebSocketUseCases
import com.example.ktor_chat_app.screen_chat.domain.use_case.modify_chat_use_case.ModifyChatUseCases
import com.example.ktor_chat_app.screen_contact.domain.use_case.ContactUseCases
import com.example.ktor_chat_app.core.utility.DispatcherProvider
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
    private val insDelChatUseCases: ModifyChatUseCases,
    private val contactUseCases: ContactUseCases,
):ViewModel() {

    private var contactsUpdateJob: Job? = null
    private var chatUpdateJob: Job? = null

    // private
     fun observeBaseModels() {
        viewModelScope.launch(dispatchers.io) {
            webSocketUseCases.observeBaseModel().collect{ baseModel ->
                Log.d("kkkk","got base"
                )
                when (baseModel) {

                    is ChatMessage -> {
                        Log.d("goot","got message")
                        chatUpdateJob?.join()
                        chatUpdateJob = viewModelScope.launch(dispatchers.io) {
                            insDelChatUseCases.insertChat(
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
                        Log.d("goot","got seen")
                        chatUpdateJob?.join()
                        chatUpdateJob = viewModelScope.launch(dispatchers.io) {
                            insDelChatUseCases.messageSeenUpdate(
                                messageId = baseModel.messageId,
                            )
                        }
                    }

                    is MessageDelivered -> {
                        Log.d("goot","got deloivered")
                            chatUpdateJob?.join()
                            chatUpdateJob = viewModelScope.launch(dispatchers.io) {
                                insDelChatUseCases.messageDeliveredUpdate(
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

    //private
    fun observeConnectionEvent(){
        viewModelScope.launch(dispatchers.io) {
            webSocketUseCases.observeConnectionEvents().collect{ connectionEvent->
                when(connectionEvent){

                    is WebSocket.Event.OnConnectionOpened<*> -> {
                        Log.d("llll","con")
                    }

                    is WebSocket.Event.OnConnectionFailed -> {
                        Log.d("llll","dis")
                    }

                    is WebSocket.Event.OnConnectionClosed -> {
                        Log.d("llll","dis")
                    }
                    else -> Unit
                }
            }
        }
    }

//    init {
//        observeConnectionEvent()
//        observeBaseModels()
//    }
}
