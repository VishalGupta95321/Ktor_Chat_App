package com.example.ktor_chat_app.main.webSocketUseCases

import com.example.ktor_chat_app.data.remote.model.MessageSeen
import com.example.ktor_chat_app.main.AppRepository
import javax.inject.Inject

class SendMessageSeen @Inject constructor(
    private val repository: AppRepository
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