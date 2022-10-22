package com.example.ktor_chat_app.web_socket.data.remote.request

import com.example.ktor_chat_app.core.utility.Constants.TYPE_REGISTER_USER
import com.example.ktor_chat_app.web_socket.data.remote.req_and_res.BaseModel

data class RegisterUserRequest(
    val name: String,
    val id: String,
): BaseModel(TYPE_REGISTER_USER)

class InvalidRequestException(message:String): Exception(message)
