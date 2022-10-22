package com.example.ktor_chat_app.presentation.chat_screen

data class ContactDataState(
    val name : String = "",
    val profilePic : String? = null,
    val onlineStatus : String = ""
)