package com.example.ktor_chat_app.screen_chat.presentation

data class ChatState(
    val message : String = "",
    val time: String = "",
    val fromId: String = "",
    val messageId : String = "",
    var delivered: Boolean = false,
    var messageSeen: Boolean = false,
    var deleted : Boolean = false,
    val incoming:Boolean = false
)