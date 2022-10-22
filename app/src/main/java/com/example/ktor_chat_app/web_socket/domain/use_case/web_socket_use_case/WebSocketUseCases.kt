package com.example.ktor_chat_app.web_socket.domain.use_case.web_socket_use_case

data class WebSocketUseCases(
    val observeConnectionEvents: ObserveConnectionEvents,
    val observeBaseModel : ObserveBaseModel,
    val sendBaseModel : SendBaseModel,
    val sendMessageDeliveredUpdate: SendMessageDelivered,
    val sendChatToServer: SendChat,
    val checkingContactRegistered: IsContactRegistered,
    val sendMessageSeenUpdate: SendMessageSeen
)