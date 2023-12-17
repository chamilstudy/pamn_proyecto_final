package com.example.areasrecreativasapp.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.areasrecreativasapp.ui.home.MainScreen
import com.example.areasrecreativasapp.ui.login.LoginScreen
import com.example.areasrecreativasapp.ui.login.LoginViewModel
import com.example.areasrecreativasapp.ui.register.RegisterScreen
import com.example.areasrecreativasapp.ui.register.RegisterViewModel

@Composable
fun NavGraph(
    navController : NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavScreenDir.Login.route
    ) {
        composable(
            route = NavScreenDir.Main.route
        ) {
            MainScreen(navController)
        }

        composable(
            route = NavScreenDir.Login.route
        ) {
            LoginScreen(LoginViewModel(), navController)
        }

        composable(
            route = NavScreenDir.Register.route
        ) {
            RegisterScreen(RegisterViewModel(), navController)
        }
    }
}