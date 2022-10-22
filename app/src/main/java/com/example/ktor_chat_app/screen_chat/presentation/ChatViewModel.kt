package com.example.ktor_chat_app.presentation.chat_screen


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktor_chat_app.domain.use_cases.webSocketUseCases.WebSocketUseCases
import com.example.ktor_chat_app.screen_chat.domain.use_case.modify_chat_use_case.ModifyChatUseCases
import com.example.ktor_chat_app.screen_chat.domain.use_case.retrieve_chat_use_case.RetrieveChatUseCases
import com.example.ktor_chat_app.screen_contact.domain.use_case.ContactUseCases
import com.example.ktor_chat_app.utility.DispatcherProvider
import com.example.ktor_chat_app.utility.clientId
import com.example.ktor_chat_app.utility.dateFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val webSocketUseCases: WebSocketUseCases,
    private val isnDelUpdChatUseCases: ModifyChatUseCases,
    private val retrieveChatUseCases: RetrieveChatUseCases,
    private val contactUseCases: ContactUseCases,
    private val dataStore: DataStore<Preferences>,
    private val dispatcher: DispatcherProvider,
) : ViewModel() {

    private var myId : String? = null

    private val _contactData  = mutableStateOf(ContactDataState(
        onlineStatus = "coming soon"
    ))
    val contactData : State<ContactDataState> = _contactData

    private val _textFieldValue = mutableStateOf(ChatTextFieldState(
        label = "Your Message"
    ))
    val textFieldValue : State<ChatTextFieldState> = _textFieldValue

    private val _chatList = mutableStateOf(listOf(ChatState()))
    val chatList: State<List<ChatState>> = _chatList


    fun onEvent(events: ChatEvents){
        when(events){

            is ChatEvents.TextFieldValueChange ->{
                _textFieldValue.value = _textFieldValue.value.copy(
                    text = events.text
                )
            }

            is ChatEvents.SendMessage ->{
                viewModelScope.launch {

                    val uuid = UUID.randomUUID().toString()

                    webSocketUseCases.sendChatToServer(
                        events.message,events.sendToId,uuid
                    )

                    isnDelUpdChatUseCases.insertChat(
                        events.message,events.sendToId,events.roomId,uuid
                    )

                    _textFieldValue.value = ChatTextFieldState(
                        text = ""
                    )
                    contactUseCases.updateContactActiveStatus(events.sendToId)
                }
            }

            is ChatEvents.UpdateSeen ->{
                viewModelScope.launch(dispatcher.io) {
                    webSocketUseCases.sendMessageSeenUpdate(
                        fromId = events.fromId,
                        messageId = events.messageId,
                        isIncoming = events.isIncoming
                    )
                }
            }

            is ChatEvents.UpdateDeleted ->{
                viewModelScope.launch(dispatcher.io) {
                    isnDelUpdChatUseCases.messageDeleteUpdate(events.messageId)
                }
            }
        }
    }

     private fun retrieveAllChats(roomId:String) {
        viewModelScope.launch {
            retrieveChatUseCases.retrieveAllChatsByRoom(roomId).collect { chatMessages ->
                _chatList.value = chatMessages.map {
                    ChatState(
                        messageSeen = it.messageSeen,
                        delivered = it.delivered,
                        deleted = it.deleted,
                        messageId = it.messageId,
                        message = it.message,
                        time = dateFormat(it.timestamp),
                        incoming = it.toId==myId,
                        fromId = it.fromId
                    )
                }
            }
        }
    }

     private fun retrieveContact(contactId:String){
        viewModelScope.launch{
            contactUseCases.getContactWithId(contactId).collect{ contact->
                _contactData.value = _contactData.value.copy(
                    name = contact.name,
                )
            }
        }
    }

    private fun myId(){
        viewModelScope.launch {
            myId=dataStore.clientId()
        }
    }

    init {
//        retrieveContact("+919682824770")
//        retrieveAllChats("+919682824770")
        retrieveContact("+919532169104")
        retrieveAllChats("+919532169104")
        myId()
    }
}
