package com.example.ktor_chat_app.data.remote.model

import com.example.ktor_chat_app.utility.Constants.TYPE_CONTACT_AVAILABLE

data class ContactAvailable(
    val contacts : List<String>
):BaseModel(TYPE_CONTACT_AVAILABLE)