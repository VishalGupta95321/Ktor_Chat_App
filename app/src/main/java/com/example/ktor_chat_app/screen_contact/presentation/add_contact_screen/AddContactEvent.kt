package com.example.ktor_chat_app.screen_contact.presentation.add_contact_screen

sealed class AddContactEvent {
    data class AddContacts(val contacts:List<String>): AddContactEvent()
}