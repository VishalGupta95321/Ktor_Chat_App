package com.example.ktor_chat_app.presentation.login_signup_screen.navGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.ktor_chat_app.presentation.Graph
import com.example.ktor_chat_app.presentation.login_signup_screen.LoginSignUpViewModel
import com.example.ktor_chat_app.presentation.login_signup_screen.OtpVerificationScreen
import com.example.ktor_chat_app.presentation.login_signup_screen.components.GenerateOtpScreen


fun NavGraphBuilder.authGraph(
    viewModel: LoginSignUpViewModel,
    navController: NavHostController
){
    navigation(
        route = Graph.AuthGraph.root,
        startDestination = AuthScreens.GenerateOtoScreen.value
    ){
        composable(
            route = AuthScreens.GenerateOtoScreen.value
        ){
            GenerateOtpScreen(
                viewModel = viewModel,
                navigateToVerificationScreen = {
                    navController.navigate(AuthScreens.OtpVerificationAndLoginScreen.value)
                }
            )
        }

        composable(
            route = AuthScreens.OtpVerificationAndLoginScreen.value
        ){
            OtpVerificationScreen(
                viewModel = viewModel,
                navigateToHome = {
                    navController.popBackStack(
                        route = AuthScreens.GenerateOtoScreen.value,
                        inclusive = true
                    )
                    //todo
                    navController.navigate(Graph.HomeGraph.root)
                }
            )
        }
    }
}

sealed class AuthScreens(val value : String){
    object GenerateOtoScreen:AuthScreens("generate_otp_screen")
    object OtpVerificationAndLoginScreen:AuthScreens("otp_verify_login_screen")
}