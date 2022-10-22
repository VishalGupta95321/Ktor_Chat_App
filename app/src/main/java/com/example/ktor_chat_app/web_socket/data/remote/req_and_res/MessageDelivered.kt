package com.example.ktor_chat_app.web_socket.data.remote.req_and_res

import com.example.ktor_chat_app.core.utility.Constants.TYPE_MESSAGE_DELIVERED


data class MessageDelivered(val messageId : String , val toId : String): BaseModel(TYPE_MESSAGE_DELIVERED)