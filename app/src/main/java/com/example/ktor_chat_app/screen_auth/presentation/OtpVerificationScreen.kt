package com.example.ktor_chat_app.presentation.login_signup_screen

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.ktor_chat_app.R
import com.example.ktor_chat_app.screen_auth.presentation.AuthEvents
import com.example.ktor_chat_app.screen_auth.presentation.AuthViewModel
import com.example.ktor_chat_app.screen_auth.presentation.UiEvent
import com.example.ktor_chat_app.screen_auth.presentation.components.CustomButton
import com.example.ktor_chat_app.screen_auth.presentation.components.CustomTextField
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpVerificationScreen(
    viewModel: AuthViewModel,
    navigateToHome:()->Unit
) {
    val otpTextState = viewModel.smsCode.value
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

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

                    is UiEvent.LoginSuccessful -> {
                        snackBarHostState.showSnackbar("Verification Successful ⚡ ")
                        navigateToHome()
                    }

                    is UiEvent.OtpSent -> {
                        snackBarHostState.showSnackbar("Otp was sent to ${viewModel.contact.value.text}!!")
                    }

                    else -> {}
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
                painter = painterResource(id = R.drawable.otp_verification),
                contentDescription = "Chat_logo"
            )

            Spacer(modifier = Modifier.height(160.dp))

            CustomTextField(
                text = otpTextState.text,
                label = otpTextState.label,
                onValueChange = { viewModel.onEvent(AuthEvents.EnteredOtpEvent(it))},
                keyboardType = KeyboardType.Number
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomButton(
                onClick = {
                    viewModel.onEvent(
                        AuthEvents.CompleteLoginEvent(
                            otpTextState.text
                        )
                    )
                },
                label = "Verify & Login"
            )
        }
    }
}
