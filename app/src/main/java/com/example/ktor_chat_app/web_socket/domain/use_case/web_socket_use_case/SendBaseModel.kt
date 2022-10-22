package com.example.ktor_chat_app.main.webSocketUseCases

import com.example.ktor_chat_app.data.remote.model.BaseModel
import com.example.ktor_chat_app.main.AppRepository
import javax.inject.Inject

class SendBaseModel @Inject constructor(
    private val repository: AppRepository
){
    suspend operator fun invoke(data:BaseModel){
        repository.sendToWebSocket(data)
    }
}