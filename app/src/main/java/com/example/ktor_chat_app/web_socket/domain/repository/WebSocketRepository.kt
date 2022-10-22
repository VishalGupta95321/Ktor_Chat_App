package com.example.ktor_chat_app.web_socket.domain.repository

import com.example.ktor_chat_app.web_socket.data.remote.req_and_res.BaseModel
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow

interface WebSocketRepository {
    suspend fun observeWebSocketConnectionEvents(): Flow<WebSocket.Event>
    suspend fun sendToWebSocket(data: BaseModel)
    suspend fun observeFromWebSocket(): Flow<BaseModel>
}

//    suspend fun getActiveContactIds(): Flow<List<String>>
//    suspend fun getContactByID(id:String):Flow<UserEntity>
//suspend fun getAllContacts(): Flow<List<UserEntity>>
//  suspend fun addContactToDatabase(contact:UserEntity)