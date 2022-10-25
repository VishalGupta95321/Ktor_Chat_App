package com.example.ktor_chat_app.screen_chat.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.ktor_chat_app.data.local.ChatDao
import com.example.ktor_chat_app.screen_chat.data.repository.ChatRepositoryImpl
import com.example.ktor_chat_app.screen_chat.domain.repository.ChatRepository
import com.example.ktor_chat_app.screen_chat.domain.use_case.modify_chat_use_case.*
import com.example.ktor_chat_app.screen_chat.domain.use_case.retrieve_chat_use_case.RetrieveAllChatsByRoom
import com.example.ktor_chat_app.screen_chat.domain.use_case.retrieve_chat_use_case.RetrieveChatById
import com.example.ktor_chat_app.screen_chat.domain.use_case.retrieve_chat_use_case.RetrieveChatUseCases
import com.example.ktor_chat_app.web_socket.data.remote.webScoketApi.ChatApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object ChatModule {

    @Singleton
    @Provides
    fun provideChatRepository(
        api: ChatApi,
        database: ChatDao
    ): ChatRepository {
        return ChatRepositoryImpl(database)
    }

    @Singleton
    @Provides
    fun provideRetrieveChatUseCases(repository: ChatRepository): RetrieveChatUseCases {
        return RetrieveChatUseCases(
            retrieveAllChatsByRoom = RetrieveAllChatsByRoom(repository),
            retrieveChatById = RetrieveChatById(repository)
        )
    }

    @Singleton
    @Provides
    fun provideModifyChatUseCases(
        repository: ChatRepository,
        datastore: DataStore<Preferences>
    ): ModifyChatUseCases {
        return ModifyChatUseCases(
            messageDeleteUpdate = UpdateMessageDeleted(repository),
            messageDeliveredUpdate = UpdateMessageDelivered(repository),
            deleteAllChatsBYRoom = DeleteChatsByRoom(repository),
            messageSeenUpdate = UpdateMessageSeen(repository),
            insertChat = InsertChat(repository,datastore)
        )
    }

}





