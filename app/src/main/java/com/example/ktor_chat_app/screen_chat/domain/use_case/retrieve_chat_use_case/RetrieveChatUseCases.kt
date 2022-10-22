package com.example.ktor_chat_app.presentation.chat_screen.use_cases.retrieve_chat_use_cases

data class RetrieveChatUseCases(
    val retrieveAllChatsByRoom: RetrieveAllChatsByRoom,
    val retrieveChatById: RetrieveChatById,
)