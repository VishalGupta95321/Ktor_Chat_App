package com.example.ktor_chat_app.screen_auth.domain.use_case

import com.example.ktor_chat_app.screen_auth.domain.repository.AuthRepository
import javax.inject.Inject

class SaveCredentialsToDatastore @Inject constructor(
    val repository : AuthRepository
) {
    suspend operator fun invoke(userId:String, userName: String)
    = repository.saveUserToDatabase(userId, userName)
}
