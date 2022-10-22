package com.example.ktor_chat_app.screen_home.presentation.contact_list_screen

data class ActiveContactState(
    val name : String  = "",
    val userId:String = "",
    val profilePic : String? = null
)