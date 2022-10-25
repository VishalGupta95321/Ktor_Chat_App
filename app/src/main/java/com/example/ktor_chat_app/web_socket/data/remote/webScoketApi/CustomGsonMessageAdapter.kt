package com.example.ktor_chat_app.web_socket.data.remote.webScoketApi


import com.example.ktor_chat_app.core.utility.Constants.TYPE_BLOCK_USER_REQUEST
import com.example.ktor_chat_app.core.utility.Constants.TYPE_CHAT_MESSAGE
import com.example.ktor_chat_app.core.utility.Constants.TYPE_CONNECT_TO_SERVER
import com.example.ktor_chat_app.core.utility.Constants.TYPE_CONTACT_AVAILABLE
import com.example.ktor_chat_app.core.utility.Constants.TYPE_MESSAGE_DELIVERED
import com.example.ktor_chat_app.core.utility.Constants.TYPE_MESSAGE_SEEN
import com.example.ktor_chat_app.core.utility.Constants.TYPE_REGISTER_USER
import com.example.ktor_chat_app.core.utility.Constants.TYPE_UNBLOCK_USER_REQUEST
import com.example.ktor_chat_app.core.utility.Constants.TYPE_USER
import com.example.ktor_chat_app.web_socket.data.remote.req_and_res.BaseModel
import com.example.ktor_chat_app.web_socket.data.remote.req_and_res.MessageDelivered
import com.example.ktor_chat_app.web_socket.data.remote.req_and_res.MessageSeen
import com.example.ktor_chat_app.web_socket.data.remote.request.*
import com.example.ktor_chat_app.web_socket.data.remote.responce.ChatMessage
import com.example.ktor_chat_app.web_socket.data.remote.responce.User
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.tinder.scarlet.Message
import com.tinder.scarlet.MessageAdapter
import java.lang.reflect.Type

@Suppress("UNCHECKED_CAST")
class CustomGsonMessageAdapter<T> private constructor(
    private val gson: Gson
) : MessageAdapter<T> {

    override fun fromMessage(message: Message): T {

        val stringValue = when (message) {
            is Message.Text -> message.value
            is Message.Bytes -> message.value.toString()
        }
        val jsonObject = JsonParser.parseString(stringValue).asJsonObject
        val type = when (jsonObject.get("type").asString) {
            TYPE_CHAT_MESSAGE -> ChatMessage::class.java
            TYPE_MESSAGE_SEEN -> MessageSeen::class.java
            TYPE_MESSAGE_DELIVERED -> MessageDelivered::class.java
            TYPE_USER -> User::class.java
            else -> ChatMessage::class.java
        }
        val obj = gson.fromJson(stringValue, type)
        return obj as T
    }

    override fun toMessage(data: T): Message {
        var convertedData = data as BaseModel
        convertedData = when (convertedData.type) {
            TYPE_CHAT_MESSAGE -> convertedData as ChatMessage
            TYPE_MESSAGE_SEEN -> convertedData as MessageSeen
            TYPE_MESSAGE_DELIVERED -> convertedData as MessageDelivered
            TYPE_BLOCK_USER_REQUEST -> convertedData as BlockUserRequest
            TYPE_UNBLOCK_USER_REQUEST -> convertedData as UnblockUserRequest
            TYPE_CONTACT_AVAILABLE -> convertedData as ContactAvailable
            TYPE_REGISTER_USER -> convertedData as CreateUser
            TYPE_CONNECT_TO_SERVER -> convertedData as ConnectToServer
            else -> convertedData
        }
        return Message.Text(gson.toJson(convertedData))
    }

    class Factory(
        private val gson: Gson
    ) : MessageAdapter.Factory {
        override fun create(type: Type, annotations: Array<Annotation>): MessageAdapter<*> {
            return CustomGsonMessageAdapter<Any>(gson)
        }
    }
}