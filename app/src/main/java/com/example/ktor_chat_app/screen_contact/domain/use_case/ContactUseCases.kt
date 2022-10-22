package com.example.ktor_chat_app.screen_contact.domain.use_case

data class ContactUseCases(
    val getContactWithId: GetContact,
    val insertContactInDatabase: InsertContact,
    val getAllActiveContacts : GetAllActiveContacts,
    val getAllAvailableContacts: GetAllAvailableContacts,
    val updateContactActiveStatus: UpdateContactActiveStatus
)