package com.example.ktor_chat_app.presentation.ContactsScreen.components

import androidx.compose.foundation.Image
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CustomIconButton(
    vectorImageVector: ImageVector,
    description:String,
    onClick:()->Unit,
){
    IconButton(onClick = onClick ) {
        Image(
            imageVector = vectorImageVector ,
            contentDescription = description
        )
    }
}