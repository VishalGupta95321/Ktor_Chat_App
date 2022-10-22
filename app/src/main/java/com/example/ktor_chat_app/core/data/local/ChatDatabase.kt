package com.example.ktor_chat_app.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ktor_chat_app.data.local.entity.ChatMessageEntity
import com.example.ktor_chat_app.data.local.entity.UserEntity

@Database(entities = [ChatMessageEntity::class,UserEntity::class], version = 10)

abstract class ChatDatabase : RoomDatabase() {
    abstract fun chatDao() : ChatDao
}