package com.example.ktor_chat_app.web_socket.data.remote.responce


import com.example.ktor_chat_app.core.utility.Constants.TYPE_CHAT_MESSAGE
import com.example.ktor_chat_app.web_socket.data.remote.req_and_res.BaseModel
import java.text.SimpleDateFormat
import java.util.*

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

fun ChatMessage.time():String{
    return SimpleDateFormat("kk:mm:ss", Locale.getDefault()).format(timestamp)
}
