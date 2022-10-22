package com.example.ktor_chat_app.screen_chat.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ktor_chat_app.R
import com.example.ktor_chat_app.screen_contact.presentation.components.CustomIconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreenTopAppBar(
    contactProfilePic:Painter,
    contactName:String,
    contactOnlineStatus:String
){
    SmallTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFF03DAC5),
        ),
        title = {
            Column(
                modifier = Modifier.padding(start = 10.dp
                )){
                Text(
                    modifier = Modifier
                    .width(150.dp),
                    text = contactName,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier
                    .height(20.dp),
                    text = contactOnlineStatus,
                    fontSize = 10.sp,
                    maxLines = 1,
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Image(
                    painter = contactProfilePic,
                    contentDescription = "contact_profile_pic",
                    Modifier
                        .clip(CircleShape)
                        .size(70.dp)
                )
            }
        },
        actions = {
            CustomIconButton(
                vectorImageVector = ImageVector.vectorResource(id = R.drawable.more_vert),
                description = "settings",
                onClick = {}
                )
        }
    )
}
