package com.example.ktor_chat_app.screen_contact.domain.repository

import com.example.ktor_chat_app.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    suspend fun insertContactToDatabase(contact:UserEntity)
    suspend fun getContactWithId(id:String):Flow<UserEntity>
    suspend fun getAllActiveContacts(): Flow<List<UserEntity>>
    suspend fun getAllAvailableContacts(): Flow<List<UserEntity>>
    suspend fun updateContactActiveStatus(userId:String)
}