package com.example.areasrecreativasapp.ui.bottom_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddBox
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Sos
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarModel(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarModel(
        route = "home_screen",
        title = "Home",
        icon = Icons.Rounded.Home
    )

    object Book : BottomBarModel(
        route = "book_screen",
        title = "Book",
        icon = Icons.Rounded.AddBox
    )

    object Sos : BottomBarModel(
        route = "sos_screen",
        title = "Sos",
        icon = Icons.Rounded.Sos
    )

    object Profile : BottomBarModel(
        route = "profile_screen",
        title = "Profile",
        icon = Icons.Rounded.Person
    )
}