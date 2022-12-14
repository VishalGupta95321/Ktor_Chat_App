package com.example.ktor_chat_app.web_socket.domain.use_case.web_socket_use_case

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.ktor_chat_app.core.utility.credentials
import com.example.ktor_chat_app.web_socket.data.remote.responce.ChatMessage
import com.example.ktor_chat_app.web_socket.domain.repository.WebSocketRepository
import javax.inject.Inject

class SendChat @Inject constructor(
    private val repository: WebSocketRepository,
    private val dataStore: DataStore<Preferences>
){

    suspend operator fun invoke(
        message: String,
        idToSend: String,
        uuid: String
    ){
        repository.sendToWebSocket(
            ChatMessage(
                message = message,
                messageId = uuid,
                timestamp = System.currentTimeMillis(),
                toId = idToSend,
                fromId = dataStore.credentials()[0].toString()
            )
        )
    }
}