package com.example.ktor_chat_app.screen_contact.domain.use_case

import com.example.ktor_chat_app.data.local.entity.UserEntity
import com.example.ktor_chat_app.screen_contact.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllAvailableContacts @Inject constructor(
    private val repository: ContactRepository
) {

    suspend operator fun invoke():Flow<List<UserEntity>>{
        return repository.getAllAvailableContacts()
    }
}