package com.example.ktor_chat_app.screen_home.presentation.add_contact_screen

sealed class AddContactEvent {
    data class AddContacts(val contacts:List<String>): AddContactEvent()
}