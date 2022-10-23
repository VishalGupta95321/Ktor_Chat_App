package com.example.ktor_chat_app.screen_auth.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.ktor_chat_app.data.repository.AuthRepositoryImpl
import com.example.ktor_chat_app.screen_auth.domain.repository.AuthRepository
import com.example.ktor_chat_app.screen_auth.domain.use_case.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Singleton
    @Provides
    fun provideAuth():FirebaseAuth{
        return Firebase.auth
    }

    @Singleton
    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
        datastore: DataStore<Preferences>
    ): AuthRepository {
        return AuthRepositoryImpl(datastore,auth)
    }

    @Singleton
    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository): AuthUseCases {
        return AuthUseCases(
            otpVerification = OtpVerification(authRepository),
            generateOrResendOtp = GenerateOrResendOtp(repository = authRepository),
            isUserAuthenticated = IsUserAuthenticated(repository = authRepository),
            saveCredentialsToDatastore = SaveCredentialsToDatastore(repository = authRepository),
            signOut = SignOut(repository = authRepository)
        )
    }
}