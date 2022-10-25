package com.example.ktor_chat_app.core.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.ktor_chat_app.core.presentation.components.Navigation
import com.example.ktor_chat_app.core.presentation.components.Screens
import com.example.ktor_chat_app.core.presentation.ui.theme.Ktor_Chat_AppTheme
import com.example.ktor_chat_app.core.utility.credentials
import com.example.ktor_chat_app.core.utility.dataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var destination : Screens

    private val viewModel : MainViewModel by viewModels()
    private var clientId : String? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.observeConnectionEvent()
        viewModel.observeBaseModels()

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                runBlocking {
                    clientId = dataStore.credentials()[0].toString()
                }
                false
            }
        }

        setContent {
            MaterialTheme {
                window?.statusBarColor = Color(0xFF03DAC5).toArgb()
            }
            val navController = rememberNavController()

            Ktor_Chat_AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    destination = if (clientId?.isNotEmpty() == true) {
                        Screens.HomeContactScreen
                    } else {
                        Screens.GenerateOtpScreen
                    }
                    Navigation(
                        navController = navController ,
                        startDestination = destination
                    )
                }
            }
        }
    }
}