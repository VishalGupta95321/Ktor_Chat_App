package com.example.ktor_chat_app

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.ktor_chat_app.core.ui.theme.Ktor_Chat_AppTheme
import com.example.ktor_chat_app.core.utility.clientId
import com.example.ktor_chat_app.core.utility.dataStore
import com.example.ktor_chat_app.core.utility.saveUser
import com.example.ktor_chat_app.data.local.ChatDatabase
import com.example.ktor_chat_app.navgraph.HomeGraph
import com.example.ktor_chat_app.navgraph.RootNavGraph
import com.example.ktor_chat_app.web_socket.data.remote.request.RegisterUserRequest
import com.example.ktor_chat_app.web_socket.data.remote.webScoketApi.ChatApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var chatApi: ChatApi

    @Inject
    lateinit var database: ChatDatabase


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel : MainViewModel by viewModels()
        viewModel.observeBaseModels()
        viewModel.observeConnectionEvent()

// +919532169104
        // +919682824770
//                 dataStore.clientId()
        //}
       // Log.d("client", "doe" + clientId.toString())
        lifecycleScope.launch {
            database.chatDao().updateUserActiveStatus(userId = "+919532169104")
            Log.d("kkkkk","got it")
            dataStore.saveUser("+918303168968")
            //dataStore.saveUser("+919532169104")
            Toast.makeText(this@MainActivity,dataStore.clientId(),Toast.LENGTH_LONG).show()
            delay(2000L)
            chatApi.sendBaseModel(RegisterUserRequest("ajhfjga",dataStore.clientId()))
        }


        var clientId : String? = null
        runBlocking {
            clientId = dataStore.clientId()
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
                    if (!clientId.isNullOrEmpty()){
                       HomeGraph(navController = navController)
                    }else {RootNavGraph(navController = navController)}
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
 */