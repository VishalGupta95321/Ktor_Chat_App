package com.example.ktor_chat_app.presentation.login_signup_screen.components

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CustomButton(
    onClick:()->Unit,
    label:String
    ) {

    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors
            (
            containerColor = Color(0xFF03DAC5)
        )

    ) {
        Text(text = label, color = Color.Black)
    }
}