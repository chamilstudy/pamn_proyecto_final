package com.example.areasrecreativasapp.ui.bottom_bar

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.areasrecreativasapp.ui.area_info.AreaInfoScreen
import com.example.areasrecreativasapp.ui.area_info.AreaInfoViewModel
import com.example.areasrecreativasapp.ui.home.BookScreen
import com.example.areasrecreativasapp.ui.home.HomeScreen
import com.example.areasrecreativasapp.ui.home.HomeViewModel
import com.example.areasrecreativasapp.ui.profile.ProfileScreen
import com.example.areasrecreativasapp.ui.profile.ProfileViewModel
import com.example.areasrecreativasapp.ui.sos.SosScreen
import com.example.areasrecreativasapp.ui.sos.SosViewModel

@Composable
fun BottomBarNavGraph(
    bottomBarNavGraph : NavHostController
) {
    NavHost(
        navController = bottomBarNavGraph,
        startDestination = BottomBarNavScreenDir.Home.route
    ) {

        composable(
            route = BottomBarNavScreenDir.Home.route
        ) {
            HomeScreen(viewModel(factory = HomeViewModel.factory), bottomBarNavGraph)
        }

        composable(
            route = BottomBarNavScreenDir.Area.route
        ) {
                backStackEntry ->
            AreaInfoScreen(viewModel(factory = AreaInfoViewModel.factory), backStackEntry.arguments?.getString("areaId") ?: "")
        }

        composable(
            route = BottomBarNavScreenDir.Book.route
        ) {
            BookScreen(bottomBarNavGraph)
        }

        composable(
            route = BottomBarNavScreenDir.Sos.route
        ) {
            SosScreen(viewModel(factory = SosViewModel.factory), bottomBarNavGraph)
        }

        composable(
            route = BottomBarNavScreenDir.Profile.route
        ) {
            ProfileScreen(ProfileViewModel(), bottomBarNavGraph)
        }
    }
}