package com.example.areasrecreativasapp.ui.bottom_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddBox
import androidx.compose.material.icons.rounded.Campaign
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Sos
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarNavScreenDir(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarNavScreenDir(
        route = "home_screen",
        title = "Home",
        icon = Icons.Rounded.Home
    )

    object Area : BottomBarNavScreenDir(
        route = "area_info_screen/{areaId}",
        title = "Area Info",
        icon = Icons.Rounded.Campaign
    )

    object Book : BottomBarNavScreenDir(
        route = "book_screen",
        title = "Book",
        icon = Icons.Rounded.AddBox
    )


    object Sos : BottomBarNavScreenDir(
        route = "sos_screen",
        title = "Sos",
        icon = Icons.Rounded.Sos
    )

    object Profile : BottomBarNavScreenDir(
        route = "profile_screen",
        title = "Profile",
        icon = Icons.Rounded.Person
    )
}