package com.example.ktor_chat_app.web_socket.domain.use_case.web_socket_use_case

import com.example.ktor_chat_app.data.remote.model.MessageSeen
import com.example.ktor_chat_app.web_socket.domain.repository.WebSocketRepository
import javax.inject.Inject

class SendMessageSeen @Inject constructor(
    private val repository: WebSocketRepository
) {

    suspend operator fun invoke(
        isIncoming: Boolean,
        messageId: String ,
        fromId: String){

        if (isIncoming){
            repository.sendToWebSocket(MessageSeen(
                toId = fromId,
                messageId = messageId
            ))
        }
    }
}