package com.example.ktor_chat_app.domain.use_cases.loginUseCases

import com.example.ktor_chat_app.presentation.LoginSignupScreen.AuthRepository
import javax.inject.Inject

class SaveUserToDatabase @Inject constructor(
    val repository : AuthRepository
) {
    suspend operator fun invoke(userId:String)
    = repository.saveUserToDatabase(userId)
}