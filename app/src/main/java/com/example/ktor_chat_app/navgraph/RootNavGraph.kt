package com.example.ktor_chat_app.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ktor_chat_app.navgraph.HomeGraph
import com.example.ktor_chat_app.presentation.login_signup_screen.LoginSignUpViewModel
import com.example.ktor_chat_app.presentation.login_signup_screen.navGraph.authGraph


@Composable
fun RootNavGraph(
    navController: NavHostController,
){
    val authViewModel : LoginSignUpViewModel  = hiltViewModel()
    NavHost(
        navController = navController ,
        route = Graph.Root.root,
        startDestination = Graph.AuthGraph.root,
    ){
        authGraph(
            navController = navController,
            viewModel = authViewModel
        )


//        chatScreenGraph(
//            navController = navController
//        )

        composable(
            route = Graph.HomeGraph.root
        ){
            HomeGraph(navController = navController)
        }

    }
}

sealed class Graph(val root : String) {
    object Root : Graph("root_graph")
    object AuthGraph : Graph("auth_graph")
    object HomeGraph : Graph("home_graph")
    object ChatGraph : Graph("chat_graph")
}