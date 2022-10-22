package com.example.ktor_chat_app.main

import com.example.ktor_chat_app.data.remote.model.BaseModel
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun observeWebSocketConnectionEvents(): Flow<WebSocket.Event>
    suspend fun sendToWebSocket(data: BaseModel)
    suspend fun observeFromWebSocket(): Flow<BaseModel>
}

//    suspend fun getActiveContactIds(): Flow<List<String>>
//    suspend fun getContactByID(id:String):Flow<UserEntity>
//suspend fun getAllContacts(): Flow<List<UserEntity>>
//  suspend fun addContactToDatabase(contact:UserEntity)