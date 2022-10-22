package com.example.ktor_chat_app.web_socket.data.repository

import com.example.ktor_chat_app.web_socket.data.remote.req_and_res.BaseModel
import com.example.ktor_chat_app.web_socket.data.remote.webScoketApi.ChatApi
import com.example.ktor_chat_app.web_socket.domain.repository.WebSocketRepository
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WebSocketRepositoryImpl @Inject constructor(
    private val api: ChatApi,
    ) : WebSocketRepository {

    override suspend fun observeWebSocketConnectionEvents(): Flow<WebSocket.Event> {
        return api.observeEvents()
    }

    override suspend fun sendToWebSocket(data: BaseModel){
         api.sendBaseModel(data)
    }

    override suspend fun observeFromWebSocket(): Flow<BaseModel> {
        return api.observeBaseModels()
    }
}

