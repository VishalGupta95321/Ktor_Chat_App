package com.example.ktor_chat_app.web_socket.domain.use_case.web_socket_use_case

import com.example.ktor_chat_app.data.remote.model.BaseModel
import com.example.ktor_chat_app.web_socket.domain.repository.WebSocketRepository
import javax.inject.Inject

class SendBaseModel @Inject constructor(
    private val repository: WebSocketRepository
){
    suspend operator fun invoke(data:BaseModel){
        repository.sendToWebSocket(data)
    }
}