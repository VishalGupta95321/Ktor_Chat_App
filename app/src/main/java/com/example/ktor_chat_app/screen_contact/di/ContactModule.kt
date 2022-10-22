package com.example.ktor_chat_app.screen_contact.di

import com.example.ktor_chat_app.data.local.ChatDao
import com.example.ktor_chat_app.screen_contact.data.repository.ContactRepositoryImpl
import com.example.ktor_chat_app.screen_contact.domain.repository.ContactRepository
import com.example.ktor_chat_app.screen_contact.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object ContactModule {


    @Singleton
    @Provides
    fun provideContactRepository(database: ChatDao): ContactRepository {
        return ContactRepositoryImpl(database)
    }

    @Singleton
    @Provides
    fun provideContactUseCases(repository: ContactRepository): ContactUseCases {
        return ContactUseCases(
            getContactWithId = GetContact(repository),
            insertContactInDatabase = InsertContact(repository),
            getAllActiveContacts = GetAllActiveContacts(repository),
            getAllAvailableContacts = GetAllAvailableContacts(repository),
            updateContactActiveStatus = UpdateContactActiveStatus(repository)
        )
    }
}