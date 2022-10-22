package com.example.ktor_chat_app.presentation.chat_screen.use_cases.retrieve_chat_use_cases

import com.example.ktor_chat_app.data.local.entity.ChatMessageEntity
import com.example.ktor_chat_app.screen_chat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrieveAllChatsByRoom @Inject constructor(
    private val repository: ChatRepository,
){

    suspend operator fun invoke(roomId:String): Flow<List<ChatMessageEntity>> {
        return repository.getAllChatsByRoom(roomId)
    }
}