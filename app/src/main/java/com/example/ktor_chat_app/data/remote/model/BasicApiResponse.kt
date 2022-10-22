package com.example.ktor_chat_app.data.remote.model

data class BasicApiResponse(
    val successful : Boolean,
    val message: String? = null
)