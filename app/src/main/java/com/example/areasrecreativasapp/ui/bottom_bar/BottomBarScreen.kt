package com.example.areasrecreativasapp.ui.bottom_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomBar(bottomBarNavGraph: NavController) {
    val screens = listOf(
        BottomBarModel.Home,
        BottomBarModel.Book,
        BottomBarModel.Sos,
        BottomBarModel.Profile
    )

    val navBackStackEntry by bottomBarNavGraph.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = Color(64,64,64),
        modifier = Modifier.height(80.dp)
    ) {
        screens.forEach { screen ->
            AddItem(screen = screen, currentDestination = currentDestination, bottomBarNavGraph = bottomBarNavGraph)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarModel,
    currentDestination: NavDestination?,
    bottomBarNavGraph: NavController
) {
    BottomNavigationItem(
        icon = {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(255, 202, 86))
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = screen.icon,
                    contentDescription = "Navigation Icon",
                    tint = Color(136,122,74),
                    modifier = Modifier.padding(12.dp)
                )
            }
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,

        onClick = {
            bottomBarNavGraph.navigate(screen.route)
        }
    )
}