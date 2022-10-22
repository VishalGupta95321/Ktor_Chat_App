package com.example.ktor_chat_app.presentation.chat_screen.use_cases.modify_chat_use_cases

import com.example.ktor_chat_app.screen_chat.domain.repository.ChatRepository
import javax.inject.Inject

class UpdateMessageDeleted @Inject constructor(
    private val repository: ChatRepository
) {

    suspend operator fun invoke(messageId:String){
        repository.updateMessageDeleted(messageId)
    }
}