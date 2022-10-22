package com.example.ktor_chat_app.screen_chat.presentation

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ktor_chat_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel(),
    sendToId:String,
) {
    val chatList = viewModel.chatList.value
    val contactData = viewModel.contactInfo.value
    val text = viewModel.textFieldValue.value

    Log.d("kkkkk",";;;;")

    Scaffold(
        topBar = {
            ChatScreenTopAppBar(
                contactProfilePic = painterResource(id = R.drawable.otp_verification),
                contactName = contactData.name,
                contactOnlineStatus = contactData.onlineStatus,
            )
        },
        content = { padding->
            LazyColumn(
                contentPadding = padding,
            ){
            items(items = chatList){ item->
                ChatListItem(chat = item)
                viewModel.onEvent(
                    ChatEvents.UpdateSeen(
                        fromId = item.fromId,
                        isIncoming = item.incoming,
                        messageId = item.messageId
                    )
                )
            }
        }

            },
        bottomBar = {
            SendTextField(
                label = text.label,
                text = text.text,
                onValueChange ={
                    viewModel.onEvent(
                        ChatEvents.TextFieldValueChange(it)
                    )} ,
                onSendClick = {
                    viewModel.onEvent(
                        ChatEvents.SendMessage(
                            message = it,
                            sendToId = sendToId,
                            roomId = sendToId
                        )
                    )
                }
            )
        },
    )
}

