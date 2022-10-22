package com.example.ktor_chat_app.data.remote.model


import com.example.ktor_chat_app.core.utility.Constants.TYPE_CHAT_MESSAGE

data class ChatMessage(
    val fromId : String,
    val toId : String,
    val message : String,
    val messageId : String,
    val timestamp: Long,
    var delivered: Boolean = false,
    var messageSeen: Boolean = false,
    var deleted : Boolean  = false
): BaseModel(TYPE_CHAT_MESSAGE)


