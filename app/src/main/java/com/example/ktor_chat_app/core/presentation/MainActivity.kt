package com.example.ktor_chat_app.core.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
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


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel : MainViewModel by viewModels()
        viewModel.observeConnectionEvent()

        var destination : Screens

        var clientId : String? = null

        runBlocking {
            clientId = dataStore.credentials()[0]
            Log.d("kkk",clientId.toString())
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
                    destination = if (!clientId.isNullOrEmpty()) {
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

/*

TODO - Suggestion
    1) multiple scarlet instances of web socket with multiple websocket routes
           and have to put in each feature/screen in separate data directory.
    2) multiple RETROFIT instances of HTTP api with multiple  routes

    source - phillip lackner social network twitch repo

    TODO
        and have to put in each feature/screen in separate data directory.
        3) fix navigation
        4) splash screen
        5) change .copy to .update
        6) not necessary now but if want - g = fix navigation that using multiple nav graph
 */