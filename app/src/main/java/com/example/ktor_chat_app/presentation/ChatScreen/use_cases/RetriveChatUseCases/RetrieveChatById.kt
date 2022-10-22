package com.example.ktor_chat_app.presentation.ChatScreen.use_cases.RetriveChatUseCases

import com.example.ktor_chat_app.presentation.ChatScreen.ChatRepository
import javax.inject.Inject

class RetrieveChatById @Inject constructor(
    repository: ChatRepository
){

    suspend operator fun invoke(){

    }
}