package com.example.ktor_chat_app.presentation.ChatScreen.use_cases.InsDelUpdChatUseCases

import com.example.ktor_chat_app.presentation.ChatScreen.ChatRepository
import javax.inject.Inject

class UpdateMessageDelivered @Inject constructor(
    private val repository: ChatRepository
) {

    suspend operator fun invoke(messageId:String){
        repository.updateMessageDelivered(messageId)
    }
}