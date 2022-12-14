package com.example.ktor_chat_app.screen_contact.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ktor_chat_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    title:String
){
    SmallTopAppBar(
        modifier = Modifier
        .padding(bottom = 5.dp),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFF03DAC5),
        ),

        title = {
            Text(
                color = Color(0xff005661),
                text = title,
                fontSize = 25.sp,
                fontWeight = FontWeight.W800,
                maxLines = 1,
            )
        },
        actions = {
            CustomIconButton(
                vectorImageVector = ImageVector.vectorResource(id = R.drawable.search_icon),
                description = "searchContacts",
                onClick = {}
            )
            CustomIconButton(
                vectorImageVector = ImageVector.vectorResource(id = R.drawable.more_vert),
                description = "settings",
                onClick = {}
            )
        }
    )
}