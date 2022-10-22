package com.example.ktor_chat_app.web_socket.data.remote.responce.dto


import com.example.ktor_chat_app.data.local.entity.ChatMessageEntity
import com.example.ktor_chat_app.data.local.entity.UserEntity
import com.example.ktor_chat_app.web_socket.data.remote.responce.ChatMessage
import com.example.ktor_chat_app.web_socket.data.remote.responce.User

fun ChatMessage.asDataBaseModel(roomId:String) = ChatMessageEntity(
    fromId, toId, message, messageId, timestamp, delivered, messageSeen, deleted,roomId
)
fun User.asDataBaseModel() = UserEntity(
    name = name, userId = userId, about = about, lastOnline = lastOnline, profilePhotoUri = profilePhotoUri
)