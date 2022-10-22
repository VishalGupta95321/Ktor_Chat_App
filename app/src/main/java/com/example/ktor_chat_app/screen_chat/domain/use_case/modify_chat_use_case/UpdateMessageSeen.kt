package com.example.ktor_chat_app.screen_chat.domain.use_case.modify_chat_use_case

import com.example.ktor_chat_app.screen_chat.domain.repository.ChatRepository
import javax.inject.Inject

class UpdateMessageSeen @Inject constructor(
    private val repository: ChatRepository
) {

    suspend operator fun invoke( messageId:String){
            repository.updateMessageSeen(messageId)
    }
}