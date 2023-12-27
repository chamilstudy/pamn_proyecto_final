package com.example.areasrecreativasapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.areasrecreativasapp.ui.bottom_bar.BottomBar
import com.example.areasrecreativasapp.ui.common.TransitionScreen
import com.example.areasrecreativasapp.ui.navigation.NavGraph
import com.example.areasrecreativasapp.ui.navigation.NavScreenDir
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen() {
    val navController = rememberNavController()
    val bottomBarVisibility = remember { mutableStateOf(false) }
    val auth = Firebase.auth
    val loading = remember { mutableStateOf(true) }  // Add this line

    DisposableEffect(Unit) {
        val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            loading.value = false  // Set loading to false when user state is known

            if (user != null) {
                // User is signed in
                bottomBarVisibility.value = true
                navController.navigate(NavScreenDir.Home.route)
            } else {
                // User is signed out
                navController.navigate(NavScreenDir.Login.route)
                bottomBarVisibility.value = false
            }
        }

        auth.addAuthStateListener(authStateListener)

        onDispose {
            auth.removeAuthStateListener(authStateListener)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (bottomBarVisibility.value && !loading.value) {
                BottomBar(bottomBarNavGraph = navController)
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
        ) {
            NavGraph(navController = navController)
        }
    }
}