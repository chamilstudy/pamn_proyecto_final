package com.example.areasrecreativasapp.ui.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Campaign
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.areasrecreativasapp.R
import com.example.areasrecreativasapp.data.database.Area
import com.example.areasrecreativasapp.ui.common.Header

@Composable
fun HomeScreen(
    viewModel : HomeViewModel = viewModel(factory = HomeViewModel.factory),
    navController : NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(ScrollState(0))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Header(name = stringResource(id = R.string.header_label_home))
        areasColumn(viewModel = viewModel, navController = navController)
    }
}

@Composable
fun areasColumn(viewModel : HomeViewModel, navController : NavController) {
    val areaList by viewModel.getAllAreas().collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (area in areaList) {
            areaTag(
                navController = navController,
                area = area
            )
        }
    }
}

@Composable
fun areaTag(navController: NavController, area : Area) {
    val context = LocalContext.current
    val drawableId =
        context.resources.getIdentifier(area.img, "drawable", context.packageName)
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { navController.navigate(route = "area_info_screen/${area.id}") },
        elevation = CardDefaults.cardElevation(16.dp),
        shape = RoundedCornerShape(8.dp),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Box (
            modifier = Modifier.paint(
                // Replace with your image id
                painterResource(id = drawableId),
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                    setToScale(
                        0.6f,
                        0.6f,
                        0.6f,
                        1f
                    )
                })
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (!area.isavailable) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Campaign,
                                contentDescription = stringResource(id = R.string.availability_icon),
                                tint = Color.White
                            )
                            Text(
                                text = stringResource(id = R.string.area_availability),
                                color = Color.White
                            )
                        }
                    }
                }
                Text(
                    text = area.name.uppercase(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
        }
    }
}
