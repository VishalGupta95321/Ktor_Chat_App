package com.example.ktor_chat_app.screen_auth.domain.use_case

import com.example.ktor_chat_app.web_socket.data.remote.request.RegisterUserRequest
import com.example.ktor_chat_app.screen_auth.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterToServer @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(request: RegisterUserRequest) {
      return repository.registerToServer(request)
    }
}