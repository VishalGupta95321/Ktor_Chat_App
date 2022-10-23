package com.example.ktor_chat_app.screen_auth.presentation

import android.app.Activity

sealed class AuthEvents {
    object SignOut: AuthEvents()
    data class EnteredNameEvent(val value : String) : AuthEvents()
    data class EnteredPhoneNoEvent(val value: String) : AuthEvents()
    data class GenerateOtpEvent(val activity: Activity): AuthEvents()
    data class ResendOtpEvent(val activity: Activity): AuthEvents()
    data class CompleteLoginEvent(val value: String) : AuthEvents()
    data class EnteredOtpEvent(val value: String) : AuthEvents()
}