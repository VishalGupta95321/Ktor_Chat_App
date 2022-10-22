package com.example.ktor_chat_app.data.remote.model


data class UserLastSeen(
    val userId : String,
    val timeStamp:Long
)