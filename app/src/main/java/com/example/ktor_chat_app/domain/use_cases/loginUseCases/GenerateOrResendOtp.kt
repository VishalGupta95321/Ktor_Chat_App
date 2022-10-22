package com.example.ktor_chat_app.domain.use_cases.loginUseCases

import com.example.ktor_chat_app.data.remote.model.InvalidRequestException
import com.example.ktor_chat_app.data.remote.model.RegisterUserRequest
import com.example.ktor_chat_app.presentation.LoginSignupScreen.AuthRepository
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