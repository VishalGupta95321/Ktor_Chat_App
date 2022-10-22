package com.example.ktor_chat_app.presentation.chat_screen.use_cases.retrieve_chat_use_cases

import com.example.ktor_chat_app.screen_chat.domain.repository.ChatRepository
import javax.inject.Inject

class RetrieveChatById @Inject constructor(
    repository: ChatRepository
){

    suspend operator fun invoke(){

    }
}