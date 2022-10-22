package com.example.ktor_chat_app.web_socket.data.remote.request

import com.example.ktor_chat_app.core.utility.Constants.TYPE_CONTACT_AVAILABLE
import com.example.ktor_chat_app.web_socket.data.remote.req_and_res.BaseModel

data class ContactAvailable(
    val contacts : List<String>
): BaseModel(TYPE_CONTACT_AVAILABLE)