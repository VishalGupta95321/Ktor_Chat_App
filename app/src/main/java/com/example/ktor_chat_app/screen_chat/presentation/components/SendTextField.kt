package com.example.ktor_chat_app.presentation.chat_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.ktor_chat_app.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SendTextField(
    label:String,
    text:String,
    onValueChange:(String)->Unit,
    onSendClick:(String)->Unit
) {

    val keyBoardController = LocalSoftwareKeyboardController.current

    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        OutlinedTextField(
            modifier = Modifier
                .width(
                    if (text.isBlank()){
                        400.dp
                    } else 350.dp)
                .padding(5.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            colors = TextFieldDefaults
                .outlinedTextFieldColors(
                    containerColor = Color(0xFF7DCEC6),
                    focusedBorderColor = Color(0xFF005661),
                    unfocusedBorderColor = Color(0xFF005661)
                ),
            value = text,
            onValueChange = onValueChange,
            label = { Text(text = label)},
            trailingIcon = {
                Row{
                    Image(modifier = Modifier
                        .size(30.dp)
                        .padding(end = 5.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.link_tag),
                        contentDescription = "attach_link")
                    Image(modifier = Modifier
                        .size(30.dp)
                        .padding(end = 5.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.camera_icon),
                        contentDescription = "send_image")
                }
            },
            keyboardActions = KeyboardActions(
                onDone = {keyBoardController?.hide()}
            ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
        )
        IconButton(
            onClick ={
                onSendClick(text)
            }) {
            Image(imageVector = ImageVector.vectorResource(id = R.drawable.send_button), contentDescription = "ljbnewcds")
        }
    }
}
