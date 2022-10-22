package com.example.ktor_chat_app.web_socket.domain.use_case.web_socket_use_case

import android.util.Log
import com.example.ktor_chat_app.web_socket.data.remote.req_and_res.MessageDelivered
import com.example.ktor_chat_app.web_socket.domain.repository.WebSocketRepository
import javax.inject.Inject

class SendMessageDelivered @Inject constructor(
    private val repository: WebSocketRepository
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