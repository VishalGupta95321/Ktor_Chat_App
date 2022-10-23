package com.example.ktor_chat_app.core.presentation.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ktor_chat_app.presentation.contacts_screen.composables.AddContactScreen
import com.example.ktor_chat_app.presentation.contacts_screen.composables.HomeContactScreen
import com.example.ktor_chat_app.presentation.login_signup_screen.OtpVerificationScreen
import com.example.ktor_chat_app.presentation.login_signup_screen.components.GenerateOtpScreen
import com.example.ktor_chat_app.screen_auth.presentation.AuthViewModel
import com.example.ktor_chat_app.screen_chat.presentation.ChatScreen

@Composable
fun Navigation(
    navController: NavHostController,
    startDestination: Screens,
    authViewModel: AuthViewModel = hiltViewModel()
) {

    NavHost(
        navController = navController,
        startDestination = startDestination.route,
    ){

        composable(
            route = Screens.GenerateOtpScreen.route
        ){
            GenerateOtpScreen(
                viewModel = authViewModel,
                navigateToVerificationScreen = {
                    navController.navigate(
                        Screens.OtpVerificationAndLoginScreen.route
                    )
                }
            )
        }

        composable(
            route = Screens.OtpVerificationAndLoginScreen.route
        ){
            OtpVerificationScreen(
                viewModel = authViewModel,
                navigateToHome = {
                    navController.popBackStack(
                        route = Screens.GenerateOtpScreen.route,
                        inclusive = true
                    )
                    navController.navigate(Screens.HomeContactScreen.route)
                }
            )
        }

        composable(
            route = Screens.HomeContactScreen.route
        ){
            HomeContactScreen(
                navController,
                navigateToAddContact = {
                    navController.navigate(Screens.AddContactScreen.route)
                },
            )
        }

        composable( route = Screens.AddContactScreen.route) {
            AddContactScreen(navController)
        }

        composable(
            route = Screens.ChatScreen.route,
            arguments = listOf(navArgument("clientId"){
                type = NavType.StringType
            })
        ){
            ChatScreen(sendToId = it.arguments?.getString("clientId").toString())
        }
    }
}

sealed class Screens(val route : String) {
    object GenerateOtpScreen: Screens("generate_otp_screen")
    object OtpVerificationAndLoginScreen: Screens("otp_verify_login_screen")
    object HomeContactScreen : Screens("home_screen")
    object AddContactScreen : Screens("add_contact_screen")
    object ChatScreen : Screens("chat_screen/{clientId}")
}