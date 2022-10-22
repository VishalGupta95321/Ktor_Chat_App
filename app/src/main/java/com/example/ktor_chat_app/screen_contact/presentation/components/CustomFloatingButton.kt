package com.example.ktor_chat_app.screen_home.presentation.components

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

//@Composable
//fun HomeFloatingBar() {
//    FloatingActionButton(
//        containerColor = Color.White,
//        onClick = { /* do something */ },
//    ) {
//        Row(modifier = Modifier
//            .height(50.dp)
//            .width(50.dp)
//            .background(Color.White)) {
//            Image(
//                imageVector = ImageVector.vectorResource(id = R.drawable.add_contact) ,
//                contentDescription = "Add Contact"
//            )
//        }
//    }
//}
