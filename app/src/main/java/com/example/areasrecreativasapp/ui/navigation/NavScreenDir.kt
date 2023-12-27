package com.example.areasrecreativasapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddBox
import androidx.compose.material.icons.rounded.Campaign
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Sos
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavScreenDir (
    val route: String,
    val icon: ImageVector
) {
    object Home : NavScreenDir (
        route = "home_screen",
        icon = Icons.Rounded.Home
    )

    object Register : NavScreenDir (
        route = "register_screen",
        icon = Icons.Rounded.Home
    )

    object Login : NavScreenDir (
        route = "login_screen",
        icon = Icons.Rounded.Home
    )

    object Area : NavScreenDir (
        route = "area_info_screen/{areaId}",
        icon = Icons.Rounded.Campaign
    )

    object Book : NavScreenDir (
        route = "book_screen",
        icon = Icons.Rounded.AddBox
    )

    object Sos : NavScreenDir (
        route = "sos_screen",
        icon = Icons.Rounded.Sos
    )

    object Profile : NavScreenDir (
        route = "profile_screen",
        icon = Icons.Rounded.Person
    )

    object TransitionScreen : NavScreenDir (
        route = "transition_screen",
        icon = Icons.Rounded.Person
    )
}