package com.example.ktor_chat_app.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.example.ktor_chat_app.data.local.ChatDao
import com.example.ktor_chat_app.data.local.ChatDatabase
import com.example.ktor_chat_app.data.remote.webScoketApi.ChatApi
import com.example.ktor_chat_app.data.remote.webScoketApi.CustomGsonMessageAdapter
import com.example.ktor_chat_app.data.remote.webScoketApi.FlowStreamAdapter
import com.example.ktor_chat_app.presentation.Main.AppRepositoryImpl
import com.example.ktor_chat_app.data.repository.AuthRepositoryImpl
import com.example.ktor_chat_app.presentation.Main.AppRepository
import com.example.ktor_chat_app.presentation.LoginSignupScreen.AuthRepository
import com.example.ktor_chat_app.domain.use_cases.loginUseCases.*
import com.example.ktor_chat_app.domain.use_cases.webSocketUseCases.*
import com.example.ktor_chat_app.presentation.ChatScreen.ChatRepository
import com.example.ktor_chat_app.presentation.ChatScreen.ChatRepositoryImpl
import com.example.ktor_chat_app.presentation.ChatScreen.use_cases.InsDelUpdChatUseCases.*
import com.example.ktor_chat_app.presentation.ChatScreen.use_cases.RetriveChatUseCases.RetrieveAllChatsByRoom
import com.example.ktor_chat_app.presentation.ChatScreen.use_cases.RetriveChatUseCases.RetrieveChatById
import com.example.ktor_chat_app.presentation.ChatScreen.use_cases.RetriveChatUseCases.RetrieveChatUseCases
import com.example.ktor_chat_app.presentation.ContactsScreen.ContactRepositoryImpl
import com.example.ktor_chat_app.presentation.ContactsScreen.ContactsRepository
import com.example.ktor_chat_app.presentation.ContactsScreen.use_cases.*
import com.example.ktor_chat_app.utility.Constants.RECONNECT_INTERVAL
import com.example.ktor_chat_app.utility.Constants.USE_LOCALHOST
import com.example.ktor_chat_app.utility.Constants.WS_BASE_URL
import com.example.ktor_chat_app.utility.Constants.WS_BASE_URL_LOCALHOST
import com.example.ktor_chat_app.utility.DispatcherProvider
import com.example.ktor_chat_app.utility.clientId
import com.example.ktor_chat_app.utility.dataStore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.retry.LinearBackoffStrategy
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideClientId(@ApplicationContext context: Context): String {
        return runBlocking { context.dataStore.clientId() }
    }

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context):DataStore<Preferences>{
        return context.dataStore
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ChatDatabase {
        return synchronized(this) {
            Room.databaseBuilder(
                context,
                ChatDatabase::class.java,
                "app_database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    @Singleton
    @Provides
    fun provideAppRepository(api: ChatApi): AppRepository {
        return AppRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideContactRepository(database:ChatDao): ContactsRepository {
        return ContactRepositoryImpl(database)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(
        api: ChatApi,
        auth:FirebaseAuth,
        datastore:DataStore<Preferences>
    ): AuthRepository {
        return AuthRepositoryImpl(api,datastore,auth)
    }

    @Singleton
    @Provides
    fun provideChatRepository(
        api: ChatApi,
        database: ChatDao
    ): ChatRepository {
        return ChatRepositoryImpl(database,api)
    }

    @Singleton
    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCases {
        return LoginUseCases(
            otpVerification = OtpVerification(authRepository),
            generateOrResendOtp = GenerateOrResendOtp(repository = authRepository),
            registerToServer = RegisterToServer(repository = authRepository),
            isUserAuthenticated = IsUserAuthenticated(repository = authRepository),
            saveUserToDatabase = SaveUserToDatabase(repository = authRepository),
            signOut = SignOut(repository = authRepository)
        )
    }

    @Singleton
    @Provides
    fun provideWebSocketUseCase(
        appRepository: AppRepository,
        datastore: DataStore<Preferences>
    ): WebSocketUseCases {
        return WebSocketUseCases(
            observeConnectionEvents = ObserveConnectionEvents(appRepository),
            observeBaseModel = ObserveBaseModel(appRepository),
            sendBaseModel = SendBaseModel(appRepository),
            sendMessageDeliveredUpdate = SendMessageDelivered(appRepository),
            sendChatToServer = SendChat(appRepository,datastore),
            checkingContactRegistered = IsContactRegistered(appRepository),
            sendMessageSeenUpdate = SendMessageSeen(appRepository)
        )
    }

    @Singleton
    @Provides
    fun provideUpdInsDelChatUseCases(
        repository: ChatRepository,
        datastore: DataStore<Preferences>
    ): UpdInsDelChatUseCases {
        return UpdInsDelChatUseCases(
            messageDeleteUpdate = UpdateMessageDeleted(repository),
            messageDeliveredUpdate = UpdateMessageDelivered(repository),
            deleteAllChatsBYRoom = DeleteChatsByRoom(repository),
            messageSeenUpdate = UpdateMessageSeen(repository),
            insertChat = InsertChat(repository,datastore)
        )
    }

    @Singleton
    @Provides
    fun provideRetrieveChatUseCases(repository: ChatRepository):RetrieveChatUseCases{
        return RetrieveChatUseCases(
            retrieveAllChatsByRoom = RetrieveAllChatsByRoom(repository),
            retrieveChatById = RetrieveChatById(repository)
        )
    }

    @Singleton
    @Provides
    fun contactUseCases(repository: ContactsRepository): ContactUseCases {
        return ContactUseCases(
            getContactWithId = GetContact(repository),
            insertContactInDatabase = InsertContact(repository),
            getAllActiveContacts = GetAllActiveContacts(repository),
            getAllAvailableContacts = GetAllAvailableContacts(repository),
            updateContactActiveStatus = UpdateContactActiveStatus(repository)
        )
    }

    @Singleton
    @Provides
    fun provideChatDao(database: ChatDatabase): ChatDao {
        return database.chatDao()
    }

    @Singleton
    @Provides
    fun provideChatApi(
        app: Application,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): ChatApi {
        return Scarlet.Builder()
            .backoffStrategy(LinearBackoffStrategy(RECONNECT_INTERVAL))
            .lifecycle(AndroidLifecycle.ofApplicationForeground(app))
            .webSocketFactory(
                okHttpClient.newWebSocketFactory(
                    if (USE_LOCALHOST) WS_BASE_URL_LOCALHOST else WS_BASE_URL
                )
            )
            .addStreamAdapterFactory(FlowStreamAdapter.Factory)
            .addMessageAdapterFactory(CustomGsonMessageAdapter.Factory(gson))
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideAuth():FirebaseAuth{
        return Firebase.auth
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(clientId: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val url = chain.request().url.newBuilder()
                    .addQueryParameter("client_id", clientId)
                    .build()
                val request = chain.request().newBuilder()
                    .url(url)
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Singleton
    @Provides
    fun provideApplicationContext(
        @ApplicationContext context: Context
    ) = context

    @Singleton
    @Provides
    fun provideGsonInstance(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider {
        return object : DispatcherProvider {
            override val main: CoroutineDispatcher
                get() = Dispatchers.Main
            override val io: CoroutineDispatcher
                get() = Dispatchers.IO
            override val default: CoroutineDispatcher
                get() = Dispatchers.Default
        }
    }
}