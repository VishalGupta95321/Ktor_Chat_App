package com.example.ktor_chat_app.presentation.ContactsScreen.AddContactScreen

sealed class AddContactEvent {
    data class AddContacts(val contacts:List<String>): AddContactEvent()
}