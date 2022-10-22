package com.example.ktor_chat_app.data.remote.model

import com.example.ktor_chat_app.utility.Constants.TYPE_USER

data class User (
    val userId:String,
    val name: String,
    val about: String? = null,
    var lastOnline : Long? = null,
    var profilePhotoUri : String? = null
):BaseModel(TYPE_USER)