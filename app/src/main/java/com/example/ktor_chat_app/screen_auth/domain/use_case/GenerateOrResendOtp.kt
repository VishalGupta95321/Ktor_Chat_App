package com.example.ktor_chat_app.screen_auth.domain.use_case

import com.example.ktor_chat_app.web_socket.data.remote.request.InvalidRequestException
import com.example.ktor_chat_app.web_socket.data.remote.request.RegisterUserRequest
import com.example.ktor_chat_app.screen_auth.domain.repository.AuthRepository
import com.google.firebase.auth.PhoneAuthOptions
import javax.inject.Inject

class GenerateOrResendOtp @Inject constructor(
    private val repository: AuthRepository,
) {
    @Throws(InvalidRequestException::class)
    operator fun invoke(request: RegisterUserRequest, options: PhoneAuthOptions?) {
        if (request.name.isBlank()) {
            throw InvalidRequestException("Name should not be empty")
        }
        if (request.id.isBlank()) {
            throw InvalidRequestException("Invalid Contact no. !!")
        }
        if (options==null){
            throw InvalidRequestException("Invalid Contact no. !!")
        }
         repository.generateOrResendOtp(options)
    }
}