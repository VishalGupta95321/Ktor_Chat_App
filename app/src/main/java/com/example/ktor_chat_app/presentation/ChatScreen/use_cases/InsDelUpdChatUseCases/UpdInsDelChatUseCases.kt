package com.example.ktor_chat_app.presentation.ChatScreen.use_cases.InsDelUpdChatUseCases

data class UpdInsDelChatUseCases(
    val messageSeenUpdate : UpdateMessageSeen,
    val messageDeliveredUpdate: UpdateMessageDelivered,
    val deleteAllChatsBYRoom: DeleteChatsByRoom,
    val messageDeleteUpdate: UpdateMessageDeleted,
    val insertChat : InsertChat
)