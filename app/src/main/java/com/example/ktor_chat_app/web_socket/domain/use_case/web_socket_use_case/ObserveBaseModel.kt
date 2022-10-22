package com.example.ktor_chat_app.web_socket.domain.use_case.web_socket_use_case

import com.example.ktor_chat_app.web_socket.data.remote.req_and_res.BaseModel
import com.example.ktor_chat_app.web_socket.domain.repository.WebSocketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveBaseModel @Inject constructor(
    private val repository: WebSocketRepository
){
    suspend operator fun invoke() : Flow<BaseModel>{
        return repository.observeFromWebSocket()
    }
}