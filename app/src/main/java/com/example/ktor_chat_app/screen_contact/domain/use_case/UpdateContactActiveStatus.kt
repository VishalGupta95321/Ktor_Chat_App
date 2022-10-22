package com.example.ktor_chat_app.presentation.contacts_screen.use_cases

import com.example.ktor_chat_app.screen_contact.domain.repository.ContactRepository
import javax.inject.Inject

class UpdateContactActiveStatus @Inject constructor(
    private val repository: ContactRepository
) {

    suspend operator fun invoke(userId:String){
        return repository.updateContactActiveStatus(userId)
    }
}