package com.example.areasrecreativasapp.ui.area_info

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bathroom
import androidx.compose.material.icons.filled.Campaign
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.areasrecreativasapp.data.database.Area
import com.example.areasrecreativasapp.R
import com.example.areasrecreativasapp.ui.common.Header

@Composable
fun AreaInfoScreen( viewModel : AreaInfoViewModel ) {
    val areaInfo by viewModel.area.collectAsState(
        initial = Area(
        0,"","",false,false,false,false,false,false,false,0,0,0,false, "img1"
    ))

    val context = LocalContext.current
    val drawableId =
        context.resources.getIdentifier(areaInfo.img, "drawable", context.packageName)

    Column (
        Modifier
            .fillMaxSize()
            .verticalScroll(ScrollState(0))
            .padding(16.dp)
    ) {
        Header(name = areaInfo.name)
        Column (
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(8.dp)),
                painter = painterResource(id = drawableId),
                contentDescription = stringResource(id = R.string.area_image_content),
                contentScale = ContentScale.Crop
            )
            GeneralActions(viewModel, areaInfo.isavailable, areaInfo)
            GeneralInformation(description = areaInfo.description)
            Facilities(
                security = areaInfo.security,
                parkingforcars = areaInfo.parkingforcars,
                parkingforcampers = areaInfo.parkingforcampers,
                bathroom = areaInfo.bathrooms,
                water = areaInfo.water,
                furnace = areaInfo.furnace,
                picnic = areaInfo.picnic
            )
            Capacities(
                modifier = Modifier,
                persons = areaInfo.capacitypeople,
                campers = areaInfo.capacitycampers,
                tents = areaInfo.capacitytents
            )
        }
    }
}

@Composable
fun GeneralActions(viewModel : AreaInfoViewModel, isAvailable : Boolean, areaInfo : Area) {
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Notice(isAvailable = isAvailable)
        BookButton(viewModel = viewModel, isAvailable = isAvailable, areaInfo = areaInfo)
    }
}

@Composable
fun GeneralInformation(description : String) {
    Card (
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Column (
            modifier = Modifier.padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.area_info_description).uppercase(),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = description
            )
        }
    }
}

@Composable
fun CollapsibleSection(
    title: String,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card (
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(32.dp)
        ) {
            Text(
                text = title.uppercase(),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        if (expanded) {
            Column (
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                content()
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun Facilities(
    security : Boolean,
    parkingforcars : Boolean,
    parkingforcampers : Boolean,
    bathroom : Boolean,
    water : Boolean,
    furnace : Boolean,
    picnic : Boolean
) {
    CollapsibleSection(
        title = stringResource(id = R.string.area_info_facilities).uppercase()
    ) {

        Facility(
            name = stringResource(id = R.string.area_comodities_attributes_label_security),
            isActive = security,
            icon = Icons.Filled.Security
        )
        Facility(
            name = stringResource(id = R.string.area_comodities_attributes_label_parking),
            isActive = parkingforcars,
            icon = Icons.Filled.LocalParking
        )
        Facility(
            name = stringResource(id = R.string.area_comodities_attributes_label_campers),
            isActive = parkingforcampers,
            icon = Icons.Filled.DirectionsBus
        )
        Facility(
            name = stringResource(id = R.string.area_comodities_attributes_label_bathrooms),
            isActive = bathroom,
            icon = Icons.Filled.Bathroom
        )
        Facility(
            name = stringResource(id = R.string.area_comodities_attributes_label_bathrooms),
            isActive = water,
            icon = Icons.Filled.WaterDrop
        )
        Facility(
            name = stringResource(id = R.string.area_comodities_attributes_label_furnace),
            isActive = furnace,
            icon = Icons.Filled.SoupKitchen
        )
        Facility(
            name = stringResource(id = R.string.area_comodities_attributes_label_tables),
            isActive = picnic,
            icon = Icons.Filled.TableRestaurant
        )
    }
}

@Composable
fun Facility (name : String, isActive : Boolean, icon : ImageVector) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row (
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "$name",
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = name.uppercase(),
                color = MaterialTheme.colorScheme.primary
            )
        }
        Icon(
            imageVector = if (isActive) {Icons.Filled.CheckCircle} else {Icons.Filled.NotInterested},
            contentDescription = if (isActive) {
                stringResource(id = R.string.available)
            } else {
                stringResource(id = R.string.not_available)
                   },
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun Capacities(modifier: Modifier, persons : Int, campers : Int, tents : Int) {
    CollapsibleSection(title = stringResource(id = R.string.area_info_capacity).uppercase()) {
        Capacity(name = stringResource(id = R.string.area_capacity_attributes_label_persons), capacity = persons, icon = Icons.Filled.Person)
        Capacity(name = stringResource(id = R.string.area_capacity_attributes_label_campers), capacity = campers, icon = Icons.Filled.DirectionsBus)
        Capacity(name = stringResource(id = R.string.area_capacity_attributes_label_tents), capacity = tents, icon = Icons.Filled.House)
    }
}

@Composable
fun Capacity (name : String, capacity : Int, icon : ImageVector) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row (
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "$name icon",
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = name.uppercase(),
                color = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            text = capacity.toString(),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun BookButton(viewModel: AreaInfoViewModel, isAvailable: Boolean, areaInfo : Area) {
    var showDialog by remember { mutableStateOf(false) }
    val requestBookEnable : Boolean by viewModel.requestBookEnable.observeAsState(initial = false)

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { showDialog = true },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.tertiary
        ),
        enabled = isAvailable
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(id = R.string.request),
            fontWeight = FontWeight.Bold
        )
    }

    val numOfPersons : String by viewModel.numOfPersons.observeAsState(initial = "")
    val numOfCampers : String by viewModel.numOfCampers.observeAsState(initial = "")
    val numOfTents : String by viewModel.numOfTents.observeAsState(initial = "")
    val dateToEnter : String by viewModel.dateToEnter.observeAsState(initial = "")
    val dateToExit : String by viewModel.dateToExit.observeAsState(initial = "")

    if (showDialog) {
        Dialog(
            onDismissRequest = {
                showDialog = false
                viewModel.onBookReset()
            }
        ) {
            Card(
                modifier = Modifier,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column (
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "${areaInfo.name}".uppercase(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                        Text(text = stringResource(id = R.string.area_form_msg))
                    }

                    if (areaInfo.capacitypeople > 0) {
                        DialogField(
                            fieldLabel = stringResource(id = R.string.area_form_persons_field),
                            inValue = numOfPersons,
                            imeAction = ImeAction.Next
                        ) {
                            viewModel.onBookChange(
                                it,
                                numOfCampers,
                                numOfTents,
                                dateToEnter,
                                dateToExit,
                                areaInfo
                            )
                        }
                    }
                    if (areaInfo.capacitycampers > 0) {
                        DialogField(
                            fieldLabel = stringResource(id = R.string.area_form_campers_field),
                            inValue = numOfCampers,
                            imeAction = ImeAction.Next
                        ) {
                            viewModel.onBookChange(
                                numOfPersons,
                                it,
                                numOfTents,
                                dateToEnter,
                                dateToExit,
                                areaInfo
                            )
                        }
                    }
                    if (areaInfo.capacitytents > 0) {
                        DialogField(
                            fieldLabel = stringResource(id = R.string.area_form_tents_field),
                            inValue = numOfTents,
                            imeAction = ImeAction.Next
                        ) {
                            viewModel.onBookChange(
                                numOfPersons,
                                numOfCampers,
                                it,
                                dateToEnter,
                                dateToExit,
                                areaInfo
                            )
                        }
                    }
                    DialogField(
                        fieldLabel = stringResource(id = R.string.area_form_date_in_field),
                        inValue = dateToEnter,
                        imeAction = ImeAction.Next
                    ) {
                        viewModel.onBookChange(numOfPersons, numOfCampers, numOfTents, it, dateToExit, areaInfo)
                    }
                    DialogField(
                        fieldLabel = stringResource(id = R.string.date_form_date_out_field),
                        inValue = dateToExit,
                        imeAction = ImeAction.Done
                    ) {
                        viewModel.onBookChange(numOfPersons, numOfCampers, numOfTents, dateToEnter, it, areaInfo)
                    }

                    Column (
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            enabled = requestBookEnable,
                            onClick = {
                                viewModel.sendBook(
                                    areaInfo.name,
                                    numOfPersons,
                                    numOfTents,
                                    numOfCampers,
                                    dateToEnter,
                                    dateToExit
                                )
                                viewModel.onBookReset()
                                showDialog = false
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.tertiary
                            )
                        ) {
                            Text(text = stringResource(id = R.string.submit))
                        }
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                showDialog = false
                                viewModel.onBookReset()
                                      },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.onSecondary,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = stringResource(id = R.string.cancel))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DialogField(fieldLabel : String, inValue : String, imeAction : ImeAction, onTextFieldChanger : (String) -> Unit) {
    TextField (
        value = inValue,
        onValueChange = { onTextFieldChanger(it) },
        label = { Text(text = fieldLabel) },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colorScheme.primary
        )
    )
}


@Composable
fun Notice(isAvailable : Boolean) {
    if (!isAvailable) {
        Row (
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(16.dp),
                imageVector = Icons.Filled.Campaign,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(id = R.string.area_availability_extended),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}