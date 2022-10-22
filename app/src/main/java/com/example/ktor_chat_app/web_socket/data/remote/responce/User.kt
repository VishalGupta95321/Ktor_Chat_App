package com.example.ktor_chat_app.web_socket.data.remote.responce

import com.example.ktor_chat_app.core.utility.Constants.TYPE_USER
import com.example.ktor_chat_app.web_socket.data.remote.req_and_res.BaseModel

data class User (
    val userId:String,
    val name: String,
    val about: String? = null,
    var lastOnline : Long? = null,
    var profilePhotoUri : String? = null
): BaseModel(TYPE_USER)