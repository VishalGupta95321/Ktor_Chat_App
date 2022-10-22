package com.example.ktor_chat_app.presentation.ContactsScreen.use_cases

import com.example.ktor_chat_app.data.local.entity.UserEntity
import com.example.ktor_chat_app.presentation.ContactsScreen.ContactsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllActiveContacts @Inject constructor(
    private val repository: ContactsRepository
) {

    suspend operator fun invoke():Flow<List<UserEntity>>{
        return repository.getAllActiveContacts()
    }

}