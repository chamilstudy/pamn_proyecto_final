package com.example.areasrecreativasapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.areasrecreativasapp.ui.area_info.AreaInfoScreen
import com.example.areasrecreativasapp.ui.area_info.AreaInfoViewModel
import com.example.areasrecreativasapp.ui.book.BookScreen
import com.example.areasrecreativasapp.ui.common.TransitionScreen
import com.example.areasrecreativasapp.ui.home.BookViewModel
import com.example.areasrecreativasapp.ui.home.HomeScreen
import com.example.areasrecreativasapp.ui.home.HomeViewModel
import com.example.areasrecreativasapp.ui.login.LoginScreen
import com.example.areasrecreativasapp.ui.login.LoginViewModel
import com.example.areasrecreativasapp.ui.profile.ProfileScreen
import com.example.areasrecreativasapp.ui.profile.ProfileViewModel
import com.example.areasrecreativasapp.ui.register.RegisterScreen
import com.example.areasrecreativasapp.ui.register.RegisterViewModel
import com.example.areasrecreativasapp.ui.sos.SosScreen
import com.example.areasrecreativasapp.ui.sos.SosViewModel

@Composable
fun NavGraph(
    navController : NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavScreenDir.TransitionScreen.route
    ) {
        composable(
            route = NavScreenDir.Login.route
        ) {
            LoginScreen(viewModel(), navController)
        }

        composable(
            route = NavScreenDir.Register.route
        ) {
            RegisterScreen(viewModel(), navController)
        }

        composable(
            route = NavScreenDir.Home.route
        ) {
            HomeScreen(viewModel(factory = HomeViewModel.factory), navController)
        }

        composable(
            route = NavScreenDir.Area.route
        ) {
                backStackEntry ->
            AreaInfoScreen(viewModel(factory = AreaInfoViewModel.factory(backStackEntry.arguments?.getString("areaId") ?: "")))
        }

        composable(
            route = NavScreenDir.Book.route
        ) {
            BookScreen(viewModel())
        }

        composable(
            route = NavScreenDir.Sos.route
        ) {
            SosScreen(viewModel(factory = SosViewModel.factory), navController)
        }

        composable(
            route = NavScreenDir.Profile.route
        ) {
            ProfileScreen(viewModel(), navController)
        }

        composable(
            route = NavScreenDir.TransitionScreen.route
        ) {
            TransitionScreen()
        }
    }
}