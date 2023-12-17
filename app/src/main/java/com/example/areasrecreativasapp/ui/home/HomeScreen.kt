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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Campaign
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.areasrecreativasapp.data.database.Area

@Composable
fun HomeScreen(
    viewModel : HomeViewModel = viewModel(factory = HomeViewModel.factory),
    bottomBarNavController : NavController
) {
    // MAIN VIEW
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(ScrollState(0))
            .background(Color.White),
        verticalArrangement = Arrangement.Top
    ) {
        // HEADER
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row (
                modifier = Modifier
                    .background(Color(64, 64, 64))
                    .height(80.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 26.dp, horizontal = 16.dp)
                        .fillMaxWidth(),
                    text = "Areas Recreativas".uppercase(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        }
        areasColumn(modifier = Modifier, viewModel = viewModel, bottomBarNavController = bottomBarNavController)
    }
}

@Composable
fun areasColumn (modifier : Modifier, viewModel : HomeViewModel, bottomBarNavController : NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val areaList by viewModel.getAllAreas().collectAsState(initial = emptyList())
        for (area in areaList) {
            areaTag(
                modifier = Modifier,
                bottomBarNavController = bottomBarNavController,
                area = area
            )
        }

    }
}

@Composable
fun areaTag(modifier : Modifier, bottomBarNavController: NavController, area : Area) {
    val context = LocalContext.current
    val drawableId =
        context.resources.getIdentifier(area.img, "drawable", context.packageName)
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(250.dp)
        .background(Color.Gray, RoundedCornerShape(16.dp))
        .clip(RoundedCornerShape(16.dp))
        //.border(2.dp, Color.White, RoundedCornerShape(16.dp))
        .clickable { bottomBarNavController.navigate(route = "area_info_screen/${area.id}") }
        .paint(
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
        Box(
            modifier = Modifier.padding(24.dp)
        ) {
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row (
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (!area.isavailable) {
                        Row (
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Campaign,
                                contentDescription = "Not Available Icon",
                                tint = Color.White,
                                modifier = Modifier
                            )
                            Text(
                                text = "No disponible.",
                                color = Color.White,
                            )
                        }
                    }
                }
                Text(
                    text = area.name.uppercase(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}
