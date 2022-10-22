package com.example.ktor_chat_app.presentation.contacts_screen.use_cases

data class ContactUseCases(
    val getContactWithId: GetContact,
    val insertContactInDatabase: InsertContact,
    val getAllActiveContacts : GetAllActiveContacts,
    val getAllAvailableContacts: GetAllAvailableContacts,
    val updateContactActiveStatus: UpdateContactActiveStatus
)