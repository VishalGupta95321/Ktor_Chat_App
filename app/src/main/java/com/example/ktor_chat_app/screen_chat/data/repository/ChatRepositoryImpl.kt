package com.example.ktor_chat_app.screen_chat.data.repository

import com.example.ktor_chat_app.data.local.ChatDao
import com.example.ktor_chat_app.data.local.entity.ChatMessageEntity
import com.example.ktor_chat_app.web_socket.data.remote.webScoketApi.ChatApi
import com.example.ktor_chat_app.screen_chat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
   private val database: ChatDao,
   private val api: ChatApi,
): ChatRepository {

   override suspend fun getAllChatsByRoom(roomId: String): Flow<List<ChatMessageEntity>> {
      return database.getAllChatsByRoom(roomId)
   }

   override suspend fun getChatById(messageId: String): Flow<ChatMessageEntity> {
      return getChatById(messageId)
   }

   override suspend fun insertChat(chat: ChatMessageEntity) {
     return database.insertChat(chat)
   }

   override suspend fun deleteAllChatByRoom(roomId: String) {
      return database.deleteAllChatsByRoom(roomId)
   }

   override suspend fun updateMessageDelivered(messageId: String) {
      return database.updateMessageDelivered(messageId = messageId)
   }

   override suspend fun updateMessageSeen(messageId: String) {
      return database.updateMessageSeen(messageId = messageId)
   }

   override suspend fun updateMessageDeleted(messageId: String) {
      return database.updateMessageDeleted(messageId = messageId)
   }

}