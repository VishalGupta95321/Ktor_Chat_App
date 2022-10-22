package com.example.ktor_chat_app.screen_chat.presentation

sealed class ChatEvents {
    data class TextFieldValueChange(val text: String): ChatEvents()
    data class SendMessage(
        val message: String,
        val sendToId:String,
        val roomId: String
        ): ChatEvents()

    data class UpdateSeen(
        val isIncoming: Boolean,
        val messageId: String,
        val fromId : String
        ): ChatEvents()

    data class UpdateDeleted(val messageId: String): ChatEvents()
}