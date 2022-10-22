package com.example.ktor_chat_app.screen_contact.presentation.add_contact_screen

data class AvailableContactState(
    val name : String = "",
    val userId: String = "",
    val about : String? = ""
)