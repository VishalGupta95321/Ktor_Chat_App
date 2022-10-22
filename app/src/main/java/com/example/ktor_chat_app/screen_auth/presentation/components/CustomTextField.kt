package com.example.ktor_chat_app.presentation.login_signup_screen.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CustomTextField(
    text:String,
    label:String,
    modifier:Modifier = Modifier,
    singleLine:Boolean = true,
    onValueChange:(String)->Unit,
    keyboardType : KeyboardType

){
    val keyBoardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF03DAC5),
            unfocusedBorderColor = Color.Black,
            containerColor =  Color(0xFF03DAC5)
        ),
        modifier = modifier
            .width(350.dp)
            .height(90.dp)
            .padding(5.dp),
        value = text,
        onValueChange = onValueChange ,
        singleLine = singleLine,
        label =  {Text(text = label, color = Color.Black)},
        keyboardActions = KeyboardActions(
            onDone = {keyBoardController?.hide()}
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType)
    )
}