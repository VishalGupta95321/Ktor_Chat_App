package com.example.ktor_chat_app.web_socket.data.remote.webScoketApi

import com.example.ktor_chat_app.web_socket.data.remote.req_and_res.BaseModel
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow

interface ChatApi {

    @Receive
    fun observeEvents(): Flow<WebSocket.Event>

    @Send
    fun sendBaseModel(baseModel: BaseModel): Boolean

    @Receive
    fun observeBaseModels(): Flow<BaseModel>
}