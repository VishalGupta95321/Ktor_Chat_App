package com.example.ktor_chat_app.domain.use_cases.loginUseCases

import com.example.ktor_chat_app.presentation.LoginSignupScreen.AuthRepository
import com.google.firebase.auth.PhoneAuthCredential
import javax.inject.Inject

class OtpVerification @Inject constructor(
    private val authRepository: AuthRepository
    ) {
     suspend operator fun invoke(credential: PhoneAuthCredential)
     = authRepository.firebaseSignIn(credential)
}