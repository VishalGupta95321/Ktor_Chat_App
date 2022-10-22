package com.example.ktor_chat_app.presentation.ContactsScreen.AddContactScreen

data class AvailableContactState(
    val name : String = "",
    val userId: String = "",
    val about : String? = ""
)