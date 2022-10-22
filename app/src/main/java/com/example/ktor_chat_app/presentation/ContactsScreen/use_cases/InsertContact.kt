package com.example.ktor_chat_app.presentation.ContactsScreen.use_cases

import com.example.ktor_chat_app.data.local.entity.UserEntity
import com.example.ktor_chat_app.presentation.ContactsScreen.ContactsRepository
import javax.inject.Inject

class InsertContact @Inject constructor(
    private val repository: ContactsRepository
) {
    suspend operator fun invoke(data:UserEntity){
        repository.insertContactToDatabase(data)
    }
}