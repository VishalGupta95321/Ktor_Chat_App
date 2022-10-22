package com.example.ktor_chat_app.main.webSocketUseCases

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.ktor_chat_app.data.remote.model.ChatMessage
import com.example.ktor_chat_app.main.AppRepository
import com.example.ktor_chat_app.utility.clientId
import javax.inject.Inject

class SendChat @Inject constructor(
    private val repository: AppRepository,
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
                fromId = dataStore.clientId()
            )
        )
    }
}