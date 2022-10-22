package com.example.ktor_chat_app.presentation.login_signup_screen

import android.app.Activity

sealed class LoginSignUpEvents {
    object SignOut:LoginSignUpEvents()
    data class EnteredNameEvent(val value : String) : LoginSignUpEvents()
    data class EnteredPhoneNoEvent(val value: String) : LoginSignUpEvents()
    data class GenerateOtpEvent(val activity: Activity): LoginSignUpEvents()
    data class ResendOtpEvent(val activity: Activity): LoginSignUpEvents()
    data class CompleteLoginEvent(val value: String) : LoginSignUpEvents()
    data class EnteredOtpEvent(val value: String) : LoginSignUpEvents()
}