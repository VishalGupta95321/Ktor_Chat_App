package com.example.ktor_chat_app.screen_contact.domain.use_case

import com.example.ktor_chat_app.screen_contact.domain.repository.ContactRepository
import javax.inject.Inject

class UpdateContactActiveStatus @Inject constructor(
    private val repository: ContactRepository
) {

    suspend operator fun invoke(userId:String){
        return repository.updateContactActiveStatus(userId)
    }
}