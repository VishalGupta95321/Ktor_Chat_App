package com.example.ktor_chat_app.web_socket.data.remote.responce


data class UserLastSeen(
    val userId : String,
    val timeStamp:Long
)