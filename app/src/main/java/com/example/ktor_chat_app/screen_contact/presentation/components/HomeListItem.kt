package com.example.ktor_chat_app.screen_home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ktor_chat_app.R


@Composable
fun HomeListItem(
    name:String,
    onClick:()->Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White)
            .clickable{onClick()},
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            painter = painterResource(id = R.drawable.otp_verification),
            contentDescription = "contact_profile_image",
            modifier = Modifier
                .clip(CircleShape)
                .size(70.dp)
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 13.dp)
        ) {

                Text(
                    modifier = Modifier.width(150.dp),
                    text = name ,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.W700
                )

                Text(
                    modifier = Modifier.width(200.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    text = "How are You !! ",
                    fontSize = 18.sp
                )

        }
    }
}




