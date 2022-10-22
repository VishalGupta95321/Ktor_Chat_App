package com.example.ktor_chat_app.domain.use_cases.loginUseCases

import com.example.ktor_chat_app.data.remote.model.RegisterUserRequest
import com.example.ktor_chat_app.presentation.LoginSignupScreen.AuthRepository
import javax.inject.Inject

class RegisterToServer @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(request:RegisterUserRequest) {
      return repository.registerToServer(request)
    }
}