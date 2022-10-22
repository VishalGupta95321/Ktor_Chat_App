package com.example.ktor_chat_app.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ktor_chat_app.data.remote.model.ChatMessage
import com.example.ktor_chat_app.core.utility.Constants.TABLE_NAME


@Entity(tableName = TABLE_NAME)
data class ChatMessageEntity (
    val fromId : String,
    val toId : String,
    val message : String,
    @PrimaryKey
    val messageId : String,
    val timestamp: Long,
    var delivered: Boolean = false,
    var messageSeen: Boolean = false,
    var deleted : Boolean = false,
    val roomId : String
)

fun ChatMessageEntity.asDomainModel() = ChatMessage (
    fromId, toId, message, messageId, timestamp, delivered, messageSeen, deleted
)

fun List<ChatMessageEntity>.asDomainModel() : List<ChatMessage> {
    return map { ChatMessage(
        fromId = it.fromId,
        toId = it.toId,
        message = it.message,
        messageId = it.messageId,
        timestamp = it.timestamp,
        delivered = it.delivered,
        messageSeen = it.messageSeen,
        deleted = it.deleted
    ) }
}
