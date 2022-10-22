package com.example.ktor_chat_app.domain.use_cases.loginUseCases

import com.example.ktor_chat_app.presentation.LoginSignupScreen.AuthRepository
import javax.inject.Inject

class SignOut @Inject constructor(
    val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.signOut()
}