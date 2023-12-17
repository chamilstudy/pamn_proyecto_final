package com.example.areasrecreativasapp.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.areasrecreativasapp.ui.bottom_bar.BottomBarNavGraph
import com.example.areasrecreativasapp.ui.bottom_bar.BottomBarNavScreenDir

@Composable
fun ProfileScreen(viewModel: ProfileViewModel, bottomBarNavGraph: NavController) {
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Header(name = "Perfil")
        Button(
            onClick = {
                viewModel.logOut()
                bottomBarNavGraph.navigate(BottomBarNavScreenDir.Home.route)
            }
        ) {
            Text(
                text = "Log-Out"
            )
        }
    }
}

@Composable
fun Header(name : String) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(64, 64, 64))
    ){
        Text(
            modifier = Modifier.padding(vertical = 26.dp, horizontal = 16.dp),
            text = name.uppercase(),
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
    }
}