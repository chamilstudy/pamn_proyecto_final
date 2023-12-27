package com.example.areasrecreativasapp.ui.profile

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.areasrecreativasapp.R
import com.example.areasrecreativasapp.ui.common.Header
import com.example.areasrecreativasapp.ui.navigation.NavScreenDir

@Composable
fun ProfileScreen(viewModel: ProfileViewModel, bottomBarNavGraph: NavController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(ScrollState(0))
            .padding(16.dp)
    ) {
        Header(name = stringResource(id = R.string.header_label_profile))

        Button(
            onClick = {
                viewModel.logOut()
                bottomBarNavGraph.navigate(NavScreenDir.Login.route)
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.tertiary
            )
        ) {
            Text(
                text = stringResource(id = R.string.logout)
            )

        }
    }
}