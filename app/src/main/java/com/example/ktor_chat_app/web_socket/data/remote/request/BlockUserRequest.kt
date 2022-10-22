package com.example.ktor_chat_app.web_socket.data.remote.request

import com.example.ktor_chat_app.core.utility.Constants.TYPE_BLOCK_USER_REQUEST
import com.example.ktor_chat_app.web_socket.data.remote.req_and_res.BaseModel

data class BlockUserRequest(
    val fromId:String,
    val idToBeBlocked:String
): BaseModel(TYPE_BLOCK_USER_REQUEST)
