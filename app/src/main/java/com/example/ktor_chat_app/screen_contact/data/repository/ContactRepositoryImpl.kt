package com.example.ktor_chat_app.screen_contact.data.repository

import com.example.ktor_chat_app.data.local.ChatDao
import com.example.ktor_chat_app.data.local.entity.UserEntity
import com.example.ktor_chat_app.screen_contact.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val database:ChatDao
) : ContactRepository {

    override suspend fun insertContactToDatabase(contact: UserEntity) {
        return database.insertContact(contact)
    }

    override suspend fun getContactWithId(id: String): Flow<UserEntity> {
        return database.getContactWithId(id)
    }

    override suspend fun getAllActiveContacts(): Flow<List<UserEntity>> {
        return database.getAllActiveContacts()
    }

    override suspend fun getAllAvailableContacts(): Flow<List<UserEntity>> {
        return database.getAllAvailableContacts()
    }

    override suspend fun updateContactActiveStatus(userId: String) {
        database.updateUserActiveStatus(userId = userId)
    }

}

//   override suspend fun getActiveContactIds():Flow<List<String>>{
//       return database.getAllCurrentUser()
//   }
//
//   override suspend fun getContactByID(id:String):Flow<UserEntity>{
//        return database.getUserWithId(id)
//    }
//
//   override suspend fun getAllContacts(): Flow<List<UserEntity>> {
//        return database.getAllUser()
//    }
//
//   override suspend fun addContactToDatabase(contact:UserEntity){
//        return database.addUser(contact)
//    }