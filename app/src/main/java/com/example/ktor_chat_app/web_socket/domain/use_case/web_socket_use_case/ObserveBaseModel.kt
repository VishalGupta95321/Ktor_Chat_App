package com.example.ktor_chat_app.main.webSocketUseCases

import com.example.ktor_chat_app.data.remote.model.BaseModel
import com.example.ktor_chat_app.main.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveBaseModel @Inject constructor(
    private val repository: AppRepository
){
    suspend operator fun invoke() : Flow<BaseModel>{
        return repository.observeFromWebSocket()
    }
}