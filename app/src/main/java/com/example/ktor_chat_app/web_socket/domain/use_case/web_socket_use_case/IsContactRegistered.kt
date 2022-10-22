package com.example.ktor_chat_app.web_socket.domain.use_case.web_socket_use_case

import com.example.ktor_chat_app.data.remote.model.ContactAvailable
import com.example.ktor_chat_app.web_socket.domain.repository.WebSocketRepository
import javax.inject.Inject

class IsContactRegistered @Inject constructor(
    private val repository: WebSocketRepository
) {

    suspend operator fun invoke(contactId:List<String>){
        repository.sendToWebSocket(ContactAvailable(contactId))
    }

}