package com.example.areasrecreativasapp.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.areasrecreativasapp.ui.navigation.NavScreenDir
import com.example.areasrecreativasapp.ui.bottom_bar.BottomBar
import com.example.areasrecreativasapp.ui.bottom_bar.BottomBarNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val bottomBarNavController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(bottomBarNavGraph = bottomBarNavController)
        }

        //contentAlignment = Alignment.Center
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            BottomBarNavGraph(bottomBarNavGraph = bottomBarNavController)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    BookScreen(bottomBarNavController = rememberNavController())
}