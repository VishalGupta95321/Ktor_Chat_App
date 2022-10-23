package com.example.ktor_chat_app.core.utility

import java.text.SimpleDateFormat
import java.util.*

fun dateFormat(timeStamp:Long):String {
    val time = SimpleDateFormat(
        "d MMM yyyy HH:mm ",
        Locale.getDefault()
    )
    return time.format(timeStamp)
}
