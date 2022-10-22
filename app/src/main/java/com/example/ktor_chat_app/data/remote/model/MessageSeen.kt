package com.example.ktor_chat_app.data.remote.model

import com.example.ktor_chat_app.utility.Constants.TYPE_MESSAGE_SEEN


data class MessageSeen(val messageId : String ,  val toId : String):BaseModel(TYPE_MESSAGE_SEEN)