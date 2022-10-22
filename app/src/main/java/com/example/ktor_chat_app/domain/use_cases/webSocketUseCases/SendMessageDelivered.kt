package com.example.ktor_chat_app.domain.use_cases.webSocketUseCases

import android.util.Log
import com.example.ktor_chat_app.data.remote.model.MessageDelivered
import com.example.ktor_chat_app.presentation.Main.AppRepository
import javax.inject.Inject

class SendMessageDelivered @Inject constructor(
    private val repository: AppRepository
){
    suspend operator fun invoke(messageId: String ,fromId: String){
        Log.d("goot","d sent")
        repository.sendToWebSocket(
            MessageDelivered(
                messageId = messageId,
                toId = fromId
            )
        )
    }
}