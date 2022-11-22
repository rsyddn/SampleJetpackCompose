package com.example.samplejetpackcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.samplejetpackcompose.ui.screen.home.view.HomeScreen
import com.example.samplejetpackcompose.ui.screen.onboarding.view.OnBoardingScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.ONBOARDING.path) {
        composable(Routes.ONBOARDING.path) {
            OnBoardingScreen(navController = navController)
        }
        composable(Routes.HOMESCREEN.path, arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                },
                navArgument("token") {
                    type = NavType.StringType
                }
            )) {
            val email = it.arguments?.getString("email")
            val token = it.arguments?.getString("token")
            HomeScreen(
                navController = navController,
                email = email ?: "",
                token = token ?: ""
            )
        }
    }
}