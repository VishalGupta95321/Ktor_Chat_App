package com.example.ktor_chat_app.screen_contact.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun AddContactFloatingButton (
    onClick : ()->Unit,
    icon:ImageVector
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color(0xFF03DAC5)
    ) {
        Image(
            imageVector = icon ,
            contentDescription = "Add Contact"
        )
    }
}
