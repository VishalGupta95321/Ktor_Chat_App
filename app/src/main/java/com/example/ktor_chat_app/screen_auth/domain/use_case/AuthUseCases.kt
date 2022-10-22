package com.example.ktor_chat_app.screen_auth.domain.use_case

data class AuthUseCases(
    val signOut : SignOut,
    val isUserAuthenticated : IsUserAuthenticated,
    val saveUserToDatabase : SaveUserToDatabase,
    val otpVerification : OtpVerification,
    val registerToServer : RegisterToServer,
    val generateOrResendOtp: GenerateOrResendOtp,
)
