package com.example.ktor_chat_app.main.webSocketUseCases

data class WebSocketUseCases(
    val observeConnectionEvents: ObserveConnectionEvents,
    val observeBaseModel : ObserveBaseModel,
    val sendBaseModel : SendBaseModel,
    val sendMessageDeliveredUpdate: SendMessageDelivered,
    val sendChatToServer: SendChat,
    val checkingContactRegistered: IsContactRegistered,
    val sendMessageSeenUpdate: SendMessageSeen
)