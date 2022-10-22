package com.example.ktor_chat_app.data.remote.model

import com.example.ktor_chat_app.core.utility.Constants.TYPE_UNBLOCK_USER_REQUEST

data class UnblockUserRequest(
    val fromId : String,
    val idToBeUnblocked : String
):BaseModel(TYPE_UNBLOCK_USER_REQUEST)