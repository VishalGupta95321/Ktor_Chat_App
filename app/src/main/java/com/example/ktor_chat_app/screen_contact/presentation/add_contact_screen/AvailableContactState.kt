package com.example.ktor_chat_app.screen_home.presentation.add_contact_screen

data class AvailableContactState(
    val name : String = "",
    val userId: String = "",
    val about : String? = ""
)