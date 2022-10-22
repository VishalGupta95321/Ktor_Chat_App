package com.example.ktor_chat_app.presentation.ChatScreen.use_cases.RetriveChatUseCases

data class RetrieveChatUseCases(
    val retrieveAllChatsByRoom: RetrieveAllChatsByRoom,
    val retrieveChatById: RetrieveChatById,
)