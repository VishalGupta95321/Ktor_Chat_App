package com.example.ktor_chat_app.screen_auth.domain.use_case

import com.example.ktor_chat_app.screen_auth.domain.repository.AuthRepository
import com.example.ktor_chat_app.web_socket.data.remote.request.CreateUser
import javax.inject.Inject

class CreateUserRequest@Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(request: CreateUser){
        return authRepository.createUserRequest(request)
    }
}
