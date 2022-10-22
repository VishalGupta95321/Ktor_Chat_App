package com.example.ktor_chat_app.core.data.remote.model

import com.example.ktor_chat_app.core.utility.Constants.TYPE_CONTACT_AVAILABLE

data class ContactAvailable(
    val contacts : List<String>
):BaseModel(TYPE_CONTACT_AVAILABLE)