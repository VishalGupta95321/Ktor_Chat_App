package com.example.ktor_chat_app.screen_chat.domain.use_case.modify_chat_use_case

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.ktor_chat_app.data.local.entity.ChatMessageEntity
import com.example.ktor_chat_app.web_socket.data.remote.responce.dto.asDataBaseModel
import com.example.ktor_chat_app.web_socket.data.remote.responce.ChatMessage
import com.example.ktor_chat_app.screen_chat.domain.repository.ChatRepository
import com.example.ktor_chat_app.core.utility.clientId
import javax.inject.Inject

class InsertChat @Inject constructor(
    private val repository: ChatRepository,
    private val dataStore: DataStore<Preferences>
) {

    suspend operator fun invoke(
        data: ChatMessage
    ){
        repository.insertChat(
            data.asDataBaseModel(roomId = data.fromId)
        )
    }

    suspend operator fun invoke(
        message: String,
        idToSend: String,
        roomId: String,
        uuid:String
    ){
        repository.insertChat(
            ChatMessageEntity(
                message = message,
                roomId = roomId,
                messageId = uuid,
                timestamp = System.currentTimeMillis(),
                toId = idToSend,
                fromId = dataStore.clientId()
            )
        )
    }
}