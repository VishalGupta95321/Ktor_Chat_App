package com.example.ktor_chat_app.presentation.login_signup_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.ktor_chat_app.R
import com.example.ktor_chat_app.presentation.login_signup_screen.LoginSignUpEvents
import com.example.ktor_chat_app.presentation.login_signup_screen.LoginSignUpViewModel
import com.example.ktor_chat_app.presentation.login_signup_screen.UiEvent
import com.example.ktor_chat_app.presentation.main.MainActivity
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerateOtpScreen(
    viewModel:LoginSignUpViewModel,
    navigateToVerificationScreen:()->Unit,
) {
    val nameTextState = viewModel.name.value
    val phoneNoTextState = viewModel.contact.value
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val activity = LocalContext.current as MainActivity

    LaunchedEffect(key1 = true) {
        scope.launch {
            viewModel.eventFlow.collect { event ->
                when (event) {
                    is UiEvent.OnError -> {
                        snackBarHostState.showSnackbar(event.message + "☹️")
                    }

                    is UiEvent.Loading -> {
                        //TODO
                    }

                    is UiEvent.OtpSent -> {
                        navigateToVerificationScreen()
                        snackBarHostState.showSnackbar("Otp was sent to ${viewModel.contact.value.text}!!")
                    }

                    else ->{}
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState)}
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            Arrangement.Top,
            Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.padding(top = 100.dp),
                painter = painterResource(id = R.drawable.login_screen_logo),
                contentDescription = "Chat_logo"
            )

            Spacer(modifier = Modifier.height(160.dp))

            CustomTextField(
                text = nameTextState.text,
                label = nameTextState.label,
                onValueChange = { viewModel.onEvent(LoginSignUpEvents.EnteredNameEvent(it))},
                keyboardType = KeyboardType.Text
            )
            CustomTextField(
                text = phoneNoTextState.text,
                label = phoneNoTextState.label,
                onValueChange = { viewModel.onEvent(LoginSignUpEvents.EnteredPhoneNoEvent(it)) },
                keyboardType = KeyboardType.Phone
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomButton(
                onClick = {
                    viewModel.onEvent(LoginSignUpEvents.GenerateOtpEvent(activity))
                },
                label = "Continue"
            )
        }
    }
}