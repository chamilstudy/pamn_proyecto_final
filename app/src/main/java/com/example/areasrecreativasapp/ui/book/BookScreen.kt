package com.example.areasrecreativasapp.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.areasrecreativasapp.ui.bottom_bar.BottomBar
import com.example.areasrecreativasapp.ui.bottom_bar.BottomBarNavScreenDir

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookScreen(bottomBarNavController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()

        //contentAlignment = Alignment.Center
    ) {
        Box (
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.clickable { bottomBarNavController.navigate(route = BottomBarNavScreenDir.Home.route) },
                text = "Book"
            )
        }
    }
}