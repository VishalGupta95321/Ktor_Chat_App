package com.example.ktor_chat_app.domain.use_cases.webSocketUseCases

import com.example.ktor_chat_app.data.remote.model.BaseModel
import com.example.ktor_chat_app.presentation.Main.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveBaseModel @Inject constructor(
    private val repository: AppRepository
){
    suspend operator fun invoke() : Flow<BaseModel>{
        return repository.observeFromWebSocket()
    }
}