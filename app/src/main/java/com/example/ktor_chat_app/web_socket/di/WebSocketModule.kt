package com.example.ktor_chat_app.web_socket.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.ktor_chat_app.data.remote.webScoketApi.ChatApi
import com.example.ktor_chat_app.data.remote.webScoketApi.CustomGsonMessageAdapter
import com.example.ktor_chat_app.data.remote.webScoketApi.FlowStreamAdapter
import com.example.ktor_chat_app.core.utility.Constants
import com.example.ktor_chat_app.web_socket.data.repository.WebSocketRepositoryImpl
import com.example.ktor_chat_app.web_socket.domain.repository.WebSocketRepository
import com.example.ktor_chat_app.web_socket.domain.use_case.web_socket_use_case.*
import com.google.gson.Gson
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.retry.LinearBackoffStrategy
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import javax.inject.Singleton


@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object WebSocketModule {

    @Singleton
    @Provides
    fun provideWebSocketRepository(api: ChatApi): WebSocketRepository {
        return WebSocketRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideWebSocketUseCase(
        webSocketRepository: WebSocketRepository,
        datastore: DataStore<Preferences>
    ): WebSocketUseCases {
        return WebSocketUseCases(
            observeConnectionEvents = ObserveConnectionEvents(webSocketRepository),
            observeBaseModel = ObserveBaseModel(webSocketRepository),
            sendBaseModel = SendBaseModel(webSocketRepository),
            sendMessageDeliveredUpdate = SendMessageDelivered(webSocketRepository),
            sendChatToServer = SendChat(webSocketRepository,datastore),
            checkingContactRegistered = IsContactRegistered(webSocketRepository),
            sendMessageSeenUpdate = SendMessageSeen(webSocketRepository)
        )
    }

    @Singleton
    @Provides
    fun provideScarletInstance(
        app: Application,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): ChatApi {
        return Scarlet.Builder()
            .backoffStrategy(LinearBackoffStrategy(Constants.RECONNECT_INTERVAL))
            .lifecycle(AndroidLifecycle.ofApplicationForeground(app))
            .webSocketFactory(
                okHttpClient.newWebSocketFactory(
                    if (Constants.USE_LOCALHOST) Constants.WS_BASE_URL_LOCALHOST else Constants.WS_BASE_URL
                )
            )
            .addStreamAdapterFactory(FlowStreamAdapter.Factory)
            .addMessageAdapterFactory(CustomGsonMessageAdapter.Factory(gson))
            .build()
            .create()
    }

}