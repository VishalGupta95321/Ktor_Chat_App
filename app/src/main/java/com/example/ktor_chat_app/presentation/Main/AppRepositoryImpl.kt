package com.example.ktor_chat_app.presentation.Main

import com.example.ktor_chat_app.data.remote.model.BaseModel
import com.example.ktor_chat_app.data.remote.webScoketApi.ChatApi
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: ChatApi,
    ) : AppRepository {

    override suspend fun observeWebSocketConnectionEvents(): Flow<WebSocket.Event> {
        return api.observeEvents()
    }

    override suspend fun sendToWebSocket(data:BaseModel){
         api.sendBaseModel(data)
    }

    override suspend fun observeFromWebSocket(): Flow<BaseModel> {
        return api.observeBaseModels()
    }
}

