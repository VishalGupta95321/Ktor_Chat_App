package com.example.ktor_chat_app.web_socket.domain.use_case.web_socket_use_case

import com.example.ktor_chat_app.web_socket.domain.repository.WebSocketRepository
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveConnectionEvents @Inject constructor(
    private val  repository: WebSocketRepository
){
    suspend operator fun invoke(): Flow<WebSocket.Event>{
        return repository.observeWebSocketConnectionEvents()
    }
}

