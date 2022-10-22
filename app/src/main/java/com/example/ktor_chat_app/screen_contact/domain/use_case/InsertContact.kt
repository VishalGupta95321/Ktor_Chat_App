package com.example.ktor_chat_app.presentation.contacts_screen.use_cases

import com.example.ktor_chat_app.data.local.entity.UserEntity
import com.example.ktor_chat_app.screen_contact.domain.repository.ContactRepository
import javax.inject.Inject

class InsertContact @Inject constructor(
    private val repository: ContactRepository
) {
    suspend operator fun invoke(data:UserEntity){
        repository.insertContactToDatabase(data)
    }
}