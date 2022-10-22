package com.example.ktor_chat_app.presentation.ContactsScreen.HomeContactScreen

data class ActiveContactState(
    val name : String  = "",
    val userId:String = "",
    val profilePic : String? = null
)