package com.example.ktor_chat_app.presentation.chat_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ktor_chat_app.R
import com.example.ktor_chat_app.utility.Constants

@Composable
fun ChatListItem(
    chat: ChatState
) {
    var padding by remember {
        mutableStateOf(PaddingValues(start = 65.dp,top = 5.dp,end = 5.dp))
    }
    var cardColour by remember {
        mutableStateOf(Color(0xFF489769))
    }
    var boxAlignment by remember {
        mutableStateOf(Alignment.TopEnd)
    }


        when{
            chat.incoming ->{
                padding =   PaddingValues(start = 5.dp,top = 5.dp,end = 65.dp)
                cardColour =  Color(0xFF1DA794)
                boxAlignment =  Alignment.TopStart
            }
        }

    Box(
        contentAlignment = boxAlignment,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Transparent)
            .padding(padding),
    ) {
        ElevatedCard(
            modifier = Modifier
                .background(color = Color.Transparent)
                .fillMaxWidth()
                .width(100.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = cardColour
            ),
        ) {
            Text(
                modifier = Modifier
                    .padding(2.dp),
                text = chat.message,
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Start,
            )
            ChatStatus(
                messageStatus = when{
                    chat.delivered && chat.messageSeen -> Constants.TYPE_MESSAGE_SEEN
                    chat.delivered -> Constants.Chat_STATUS_TYPE_DELIVERED
                    else -> Constants.Chat_STATUS_TYPE_SENT
                },
                messageTime = chat.time,
                isIncoming = chat.incoming
            )
        }
    }
}

@Composable
fun ChatStatus(
    messageStatus: String,
    messageTime: String,
    isIncoming:Boolean
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = messageTime,
            modifier = Modifier.padding(2.dp)
        )
        if (!isIncoming){
            when(messageStatus){
                Constants.TYPE_MESSAGE_SEEN ->{
                    ChatStatusIcon(icon = ImageVector.vectorResource(id = R.drawable.chat_seen))
                }
                Constants.Chat_STATUS_TYPE_DELIVERED ->{
                    ChatStatusIcon(icon = ImageVector.vectorResource(id = R.drawable.chat_delivered))
                }
                Constants.Chat_STATUS_TYPE_SENT ->{
                    ChatStatusIcon(icon = ImageVector.vectorResource(id = R.drawable.chat_sent))
                }
            }
        }
    }

}

@Composable
fun ChatStatusIcon(icon:ImageVector) {
    Image(
        modifier = Modifier
            .size(25.dp)
            .padding(2.dp),
        imageVector = icon,
        contentDescription = null
    )
}
