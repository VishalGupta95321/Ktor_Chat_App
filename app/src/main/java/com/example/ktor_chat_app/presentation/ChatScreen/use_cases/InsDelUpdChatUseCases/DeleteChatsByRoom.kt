package com.example.ktor_chat_app.presentation.ChatScreen.use_cases.InsDelUpdChatUseCases

import com.example.ktor_chat_app.presentation.ChatScreen.ChatRepository
import javax.inject.Inject

class DeleteChatsByRoom @Inject constructor(
    repository: ChatRepository
) {
    suspend operator fun invoke(){

    }
}