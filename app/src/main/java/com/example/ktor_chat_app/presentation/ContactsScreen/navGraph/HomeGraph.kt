package com.example.ktor_chat_app.presentation.ContactsScreen.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ktor_chat_app.presentation.ChatScreen.ChatScreen
import com.example.ktor_chat_app.presentation.ContactsScreen.composables.AddContactScreen
import com.example.ktor_chat_app.presentation.ContactsScreen.composables.HomeContactScreen
import com.example.ktor_chat_app.presentation.Graph


@Composable
fun HomeGraph(
    navController: NavHostController
) {
    NavHost(
        route = Graph.HomeGraph.root,
        navController = navController,
        startDestination = HomeScreens.HomeContactScreen.route
    ){
        composable(
            route = HomeScreens.HomeContactScreen.route
        ){
            HomeContactScreen(
                navController,
                navigateToAddContact = {
                    navController.navigate(HomeScreens.AddContactScreen.route)
                },
            )
        }

        composable( route = HomeScreens.AddContactScreen.route) {
            AddContactScreen(navController)
        }

        composable(
            route = HomeScreens.ChatScreen.route,
            arguments = listOf(navArgument("clientId"){
                type = NavType.StringType
            })
        ){
            ChatScreen(sendToId = it.arguments?.getString("clientId").toString())
        }
    }
}


sealed class HomeScreens(val route : String){
    object HomeContactScreen : HomeScreens("home_screen")
    object AddContactScreen : HomeScreens("add_contact_screen")
    object ChatScreen : HomeScreens("chat_screen/{clientId}")
}