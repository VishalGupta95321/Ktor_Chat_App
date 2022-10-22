package com.example.ktor_chat_app.presentation.ContactsScreen.use_cases

import com.example.ktor_chat_app.presentation.ContactsScreen.ContactsRepository
import javax.inject.Inject

class UpdateContactActiveStatus @Inject constructor(
    private val repository: ContactsRepository
) {

    suspend operator fun invoke(userId:String){
        return repository.updateContactActiveStatus(userId)
    }
}