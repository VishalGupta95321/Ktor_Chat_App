package com.example.ktor_chat_app.screen_chat.domain.use_case.modify_chat_use_case

data class ModifyChatUseCases(
    val messageSeenUpdate : UpdateMessageSeen,
    val messageDeliveredUpdate: UpdateMessageDelivered,
    val deleteAllChatsBYRoom: DeleteChatsByRoom,
    val messageDeleteUpdate: UpdateMessageDeleted,
    val insertChat : InsertChat
)