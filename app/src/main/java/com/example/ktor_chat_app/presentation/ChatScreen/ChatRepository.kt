package com.example.ktor_chat_app.presentation.ChatScreen

import com.example.ktor_chat_app.data.local.entity.ChatMessageEntity
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun getAllChatsByRoom(roomId:String):Flow<List<ChatMessageEntity>>
    suspend fun getChatById(messageId:String):Flow<ChatMessageEntity>
    suspend fun insertChat(chat:ChatMessageEntity)
    suspend fun deleteAllChatByRoom(roomId:String)
    suspend fun updateMessageDelivered(messageId:String)
    suspend fun updateMessageSeen(messageId:String)
    suspend fun updateMessageDeleted(messageId:String)
}