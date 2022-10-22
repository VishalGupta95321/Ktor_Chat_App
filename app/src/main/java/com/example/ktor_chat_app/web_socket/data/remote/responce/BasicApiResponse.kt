package com.example.ktor_chat_app.web_socket.data.remote.responce

data class BasicApiResponse(
    val successful : Boolean,
    val message: String? = null
)