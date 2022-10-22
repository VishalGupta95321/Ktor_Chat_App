package com.example.ktor_chat_app.screen_chat.domain.use_case.retrieve_chat_use_case

data class RetrieveChatUseCases(
    val retrieveAllChatsByRoom: RetrieveAllChatsByRoom,
    val retrieveChatById: RetrieveChatById,
)