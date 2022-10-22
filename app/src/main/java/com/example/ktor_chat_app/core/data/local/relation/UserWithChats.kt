package com.example.ktor_chat_app.core.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.ktor_chat_app.core.data.local.entity.ChatMessageEntity
import com.example.ktor_chat_app.core.data.local.entity.UserEntity

data class UserWithChats(
    @Embedded val user : UserEntity,
    @Relation(
        parentColumn = "userId",
        entityColumn = "roomId"
    )
    val chats : List<ChatMessageEntity>
)