package com.example.ktor_chat_app.data.remote.dto

import com.example.ktor_chat_app.data.remote.model.ChatMessage
import java.text.SimpleDateFormat
import java.util.*

fun ChatMessage.time():String{
    return SimpleDateFormat("kk:mm:ss", Locale.getDefault()).format(timestamp)
}