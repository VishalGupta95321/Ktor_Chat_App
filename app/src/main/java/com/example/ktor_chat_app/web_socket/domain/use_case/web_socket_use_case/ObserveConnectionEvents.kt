package com.example.ktor_chat_app.main.webSocketUseCases

import com.example.ktor_chat_app.main.AppRepository
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveConnectionEvents @Inject constructor(
    private val  repository: AppRepository
){
    suspend operator fun invoke(): Flow<WebSocket.Event>{
        return repository.observeWebSocketConnectionEvents()
    }
}

