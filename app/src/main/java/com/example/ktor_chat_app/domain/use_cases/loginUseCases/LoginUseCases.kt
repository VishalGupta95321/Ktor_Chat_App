package com.example.ktor_chat_app.domain.use_cases.loginUseCases

data class LoginUseCases(
    val signOut : SignOut,
    val isUserAuthenticated : IsUserAuthenticated,
    val saveUserToDatabase : SaveUserToDatabase,
    val otpVerification : OtpVerification,
    val registerToServer : RegisterToServer,
    val generateOrResendOtp: GenerateOrResendOtp,
)
