package com.example.ktor_chat_app.data.remote.model

import com.example.ktor_chat_app.utility.Constants.TYPE_REGISTER_USER

data class RegisterUserRequest(
    val name: String,
    val id: String,
): BaseModel(TYPE_REGISTER_USER)

class InvalidRequestException(message:String): Exception(message)
