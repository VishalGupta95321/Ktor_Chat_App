package com.example.ktor_chat_app.presentation.ChatScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

fun NavGraphBuilder.chatScreenGraph(
    navController: NavHostController
){
//    navigation(
//        startDestination = ChatScreens.ChatScreen.route,
//        route = Graph.ChatGraph.root,
//    ){
//       composable(
//           route = ChatScreens.ChatScreen.route,
//           arguments = listOf(navArgument(CLIENT_ID){
//               type = NavType.StringType
//           })
//       ){
//           it.arguments?.getString(CLIENT_ID)?.let { it1 -> Log.d("jjjj", it1) }
//           ChatScreen()
//       }
//    }
}

sealed class ChatScreens(val route : String){
    //object ChatScreen : ChatScreens("chat_screen/{clientId}")
}