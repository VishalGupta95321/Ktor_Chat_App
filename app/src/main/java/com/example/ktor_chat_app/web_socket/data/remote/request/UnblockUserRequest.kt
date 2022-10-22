package com.example.ktor_chat_app.web_socket.data.remote.request

import com.example.ktor_chat_app.core.utility.Constants.TYPE_UNBLOCK_USER_REQUEST
import com.example.ktor_chat_app.web_socket.data.remote.req_and_res.BaseModel

data class UnblockUserRequest(
    val fromId : String,
    val idToBeUnblocked : String
): BaseModel(TYPE_UNBLOCK_USER_REQUEST)