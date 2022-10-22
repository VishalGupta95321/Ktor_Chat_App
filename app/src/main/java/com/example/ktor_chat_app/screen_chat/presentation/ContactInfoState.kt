package com.example.ktor_chat_app.screen_chat.presentation

data class ContactInfoState(
    val name : String = "",
    val profilePic : String? = null,
    val onlineStatus : String = ""
)