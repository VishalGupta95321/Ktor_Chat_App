package com.example.ktor_chat_app.data.remote.model

import com.example.ktor_chat_app.core.utility.Constants.TYPE_BLOCK_USER_REQUEST

data class BlockUserRequest(
    val fromId:String,
    val idToBeBlocked:String
):BaseModel(TYPE_BLOCK_USER_REQUEST)
