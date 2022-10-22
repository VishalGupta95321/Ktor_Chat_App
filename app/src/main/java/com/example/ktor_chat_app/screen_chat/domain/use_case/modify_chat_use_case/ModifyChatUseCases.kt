package com.example.ktor_chat_app.presentation.chat_screen.use_cases.modify_chat_use_cases

data class ModifyChatUseCases(
    val messageSeenUpdate : UpdateMessageSeen,
    val messageDeliveredUpdate: UpdateMessageDelivered,
    val deleteAllChatsBYRoom: DeleteChatsByRoom,
    val messageDeleteUpdate: UpdateMessageDeleted,
    val insertChat : InsertChat
)