package com.example.areasrecreativasapp.ui.navigation

sealed class NavScreenDir(
    val route: String,
    val title: String,
) {
    object Main : NavScreenDir(
        route = "main_screen",
        title = "Main"
    )

    object Login : NavScreenDir(
        route = "login_screen",
        title = "Login"
    )

    object Register : NavScreenDir(
        route = "register_screen",
        title = "Register"
    )
}