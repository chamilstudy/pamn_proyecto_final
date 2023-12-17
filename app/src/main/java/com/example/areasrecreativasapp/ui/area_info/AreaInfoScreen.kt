package com.example.areasrecreativasapp.ui.area_info

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bathroom
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocalParking
import androidx.compose.material.icons.filled.NotInterested
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.SoupKitchen
import androidx.compose.material.icons.filled.TableRestaurant
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.areasrecreativasapp.data.database.Area

@Composable
fun AreaInfoScreen(
    viewModel : AreaInfoViewModel = viewModel(factory = AreaInfoViewModel.factory),
    areaId : String
) {
    val areaInfo by viewModel.getArea(areaId.toInt()).collectAsState(initial = Area(
        0,"","",false,false,false,false,false,false,false,0,0,0,false, "img1"
    ))
    val context = LocalContext.current
    val drawableId =
        context.resources.getIdentifier(areaInfo.img, "drawable", context.packageName)

    Column (
        Modifier.verticalScroll(ScrollState(0)).background(Color.White)
    ) {
        Header(name = areaInfo.name)
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            painter = painterResource(id = drawableId),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer( modifier = Modifier.padding(vertical = 16.dp))
        GeneralInformation(modifier = Modifier, description = areaInfo.description)
        Divider( modifier = Modifier.padding(vertical = 22.dp))
        Facilities(
            modifier = Modifier,
            security = areaInfo.security,
            parkingforcars = areaInfo.parkingforcars,
            parkingforcampers = areaInfo.parkingforcampers,
            bathroom = areaInfo.bathrooms,
            water = areaInfo.water,
            furnace = areaInfo.furnace,
            picnic = areaInfo.picnic
        )
        Divider( modifier = Modifier.padding(vertical = 22.dp))
        Capacities(
            modifier = Modifier,
            persons = areaInfo.capacitypeople,
            campers = areaInfo.capacitycampers,
            tents = areaInfo.capacitytents
        )
        Spacer( modifier = Modifier.padding(vertical = 16.dp))
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

@Composable
fun GeneralInformation(modifier: Modifier, description : String) {
    Column {
        Text(
            text = "Descripcion".uppercase(),
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun Facilities(
    modifier: Modifier,
    security : Boolean,
    parkingforcars : Boolean,
    parkingforcampers : Boolean,
    bathroom : Boolean,
    water : Boolean,
    furnace : Boolean,
    picnic : Boolean
) {
    CollapsibleSection(title = "Facilidades e Instalaciones".uppercase()) {
        Facility(name = "seguridad", isActive = security, icon = Icons.Filled.Security)
        Facility(name = "aparcamientos", isActive = parkingforcars, icon = Icons.Filled.LocalParking)
        Facility(name = "caravanas", isActive = parkingforcampers, icon = Icons.Filled.DirectionsBus)
        Facility(name = "baños", isActive = bathroom, icon = Icons.Filled.Bathroom)
        Facility(name = "grifos", isActive = water, icon = Icons.Filled.WaterDrop)
        Facility(name = "hornos", isActive = furnace, icon = Icons.Filled.SoupKitchen)
        Facility(name = "mesas", isActive = picnic, icon = Icons.Filled.TableRestaurant)
    }
}

@Composable
fun Facility (name : String, isActive : Boolean, icon : ImageVector) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(16.dp),
                imageVector = icon,
                contentDescription = null
            )
            Text(text = name.uppercase())
        }
        Icon(
            modifier = Modifier.padding(16.dp),
            imageVector = if (isActive) {Icons.Filled.CheckCircle} else {Icons.Filled.NotInterested},
            contentDescription = null
        )
    }
}

@Composable
fun Capacities(modifier: Modifier, persons : Int, campers : Int, tents : Int) {
    CollapsibleSection(title = "Capacidad".uppercase()) {
        Capacity(name = "Personas", capacity = persons, icon = Icons.Filled.Person)
        Capacity(name = "Caravanas", capacity = campers, icon = Icons.Filled.DirectionsBus)
        Capacity(name = "Tiendas", capacity = tents, icon = Icons.Filled.House)
    }
}

@Composable
fun Capacity (name : String, capacity : Int, icon : ImageVector) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(16.dp),
                imageVector = icon,
                contentDescription = null
            )
            Text(text = name.toString().uppercase())
        }
        Text(
            modifier = Modifier.padding(16.dp),
            text = capacity.toString()
        )
    }
}

@Composable
fun CollapsibleSection(
    title: String,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
        ) {
            Text(
                text = title.uppercase(),
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold
            )
            Icon(
                modifier = Modifier.padding(16.dp),
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = null
            )
        }
        if (expanded) {
            content()
        }
    }
}
