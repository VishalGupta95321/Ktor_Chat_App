package com.example.ktor_chat_app.screen_auth.domain.use_case

import com.example.ktor_chat_app.screen_auth.domain.repository.AuthRepository
import com.google.firebase.auth.PhoneAuthCredential
import javax.inject.Inject

class OtpVerification @Inject constructor(
    private val authRepository: AuthRepository
    ) {
     suspend operator fun invoke(credential: PhoneAuthCredential)
     = authRepository.firebaseSignIn(credential)
}