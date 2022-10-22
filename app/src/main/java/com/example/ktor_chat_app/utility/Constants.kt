package com.example.ktor_chat_app.utility

object Constants {

    const val MAX_PHONE_NO_LENGTH = 13
    const val MAX_NAME_LENGTH = 15
    const val INDIA_TELECOM_PREFIX = "+91"
    const val TAG = "main"
    const val HOME_APP_BAR_TITLE = "Welcome !!"
    const val ADD_CONTACT_APP_BAR_TITLE = "Select Contacts"

    const val Chat_STATUS_TYPE_DELIVERED = "Chat_STATUS_TYPE_DELIVERED"
    const val Chat_STATUS_TYPE_SEEN = "Chat_STATUS_TYPE_SEEN"
    const val Chat_STATUS_TYPE_SENT = "Chat_STATUS_TYPE_SENT"

    const val CLIENT_ID = "Client_Id"

    const val TABLE_NAME  = "CHAT_LIST"
    const val TABLE_NAME2  = "USER_LIST"
    const val USE_LOCALHOST = true

    const val HTTP_BASE_URL = ""
    const val HTTP_BASE_URL_LOCALHOST = "http://192.168.232.77:8080/"

    const val WS_BASE_URL = ""
    const val WS_BASE_URL_LOCALHOST = "http://192.168.232.77:8080/ws/login"

    const val RECONNECT_INTERVAL = 3000L



    const val TYPE_USER = "TYPE_USER"
    const val TYPE_CONTACT_AVAILABLE = "TYPE_CONTACT_AVAILABLE"
    const val TYPE_CHAT_MESSAGE = "TYPE_CHAT_MESSAGE"
    const val TYPE_REGISTER_USER = "TYPE_REGISTER_USER"
    const val TYPE_MESSAGE_DELIVERED = "TYPE_MESSAGE_DELIVERED"
    const val TYPE_MESSAGE_SEEN = "TYPE_MESSAGE_SEEN"
    const val TYPE_BLOCK_USER_REQUEST = "BLOCK_USER_REQUEST"
    const val TYPE_UNBLOCK_USER_REQUEST = "TYPE_UNBLOCK_USER_REQUEST"

}