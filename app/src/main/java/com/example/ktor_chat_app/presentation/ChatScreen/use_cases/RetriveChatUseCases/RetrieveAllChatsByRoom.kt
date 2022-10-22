package com.example.ktor_chat_app.presentation.ChatScreen.use_cases.RetriveChatUseCases

import com.example.ktor_chat_app.data.local.entity.ChatMessageEntity
import com.example.ktor_chat_app.presentation.ChatScreen.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrieveAllChatsByRoom @Inject constructor(
    private val repository: ChatRepository,
){

    suspend operator fun invoke(roomId:String): Flow<List<ChatMessageEntity>> {
        return repository.getAllChatsByRoom(roomId)
    }
}