package com.example.ktor_chat_app.screen_auth.domain.repository

import com.example.ktor_chat_app.data.repository.AuthResponse
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun generateOrResendOtp(options: PhoneAuthOptions)
    suspend fun firebaseSignIn(credential: PhoneAuthCredential):Flow<AuthResponse>
    suspend fun saveUserToDatabase(userId:String, userName: String)
    suspend fun isUserAuthenticated():Boolean
    suspend fun signOut()
}