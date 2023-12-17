package com.example.areasrecreativasapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.areasrecreativasapp.ui.navigation.NavGraph
import com.example.areasrecreativasapp.ui.navigation.NavScreenDir
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen() {
    val navController = rememberNavController()

    Scaffold(
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavGraph(navController = navController)
            if (FirebaseAuth.getInstance().currentUser != null) {
                navController.navigate(NavScreenDir.Main.route)
            }
        }
    }
}