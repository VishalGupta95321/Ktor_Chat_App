package com.example.ktor_chat_app.main.webSocketUseCases

import com.example.ktor_chat_app.data.remote.model.ContactAvailable
import com.example.ktor_chat_app.main.AppRepository
import javax.inject.Inject

class IsContactRegistered @Inject constructor(
    private val repository: AppRepository
) {

    suspend operator fun invoke(contactId:List<String>){
        repository.sendToWebSocket(ContactAvailable(contactId))
    }

}