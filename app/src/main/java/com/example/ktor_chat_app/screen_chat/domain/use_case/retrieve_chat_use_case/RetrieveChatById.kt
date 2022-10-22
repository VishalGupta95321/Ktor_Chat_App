package com.example.ktor_chat_app.screen_chat.domain.use_case.retrieve_chat_use_case

import com.example.ktor_chat_app.screen_chat.domain.repository.ChatRepository
import javax.inject.Inject

class RetrieveChatById @Inject constructor(
    repository: ChatRepository
){

    suspend operator fun invoke(){

    }
}