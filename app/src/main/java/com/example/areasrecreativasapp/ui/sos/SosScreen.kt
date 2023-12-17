package com.example.areasrecreativasapp.ui.sos

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.example.areasrecreativasapp.data.database.Area
import com.example.areasrecreativasapp.data.database.Sos

@Composable
fun SosScreen(
    viewModel : SosViewModel, bottomBarNavGraph : NavController
) {
    val sosList by viewModel.getAllSos().collectAsState(initial = emptyList())

    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Header(name = "Servicios de Emergencia")
        for (sosInfo in sosList) {
            Emergency(sos = sosInfo)
            Divider()
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

@Composable
fun Emergency(sos: Sos) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                modifier = Modifier,
                text = sos.name,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp
            )
            Text(
                modifier = Modifier,
                text = sos.number.toString(),
            )
        }
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${sos.number}")
                context.startActivity(intent)
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(
                text = "Llamar",
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}