package com.example.areasrecreativasapp.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.areasrecreativasapp.R
import com.example.areasrecreativasapp.ui.navigation.NavScreenDir
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel : LoginViewModel, navController : NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Login(viewModel, navController)
    }
}

@Composable
fun Login(viewModel : LoginViewModel, navController : NavController) {
    val email : String by viewModel.email.observeAsState(initial = "")
    val password : String by viewModel.password.observeAsState(initial = "")
    val loginEnable : Boolean by viewModel.loginEnable.observeAsState(initial = false)
    var mode : MutableSet<Boolean> = remember{ mutableSetOf(false) }

    val isLoading : Boolean by viewModel.isLoading.observeAsState(initial = false)

    val coRoutineScope = rememberCoroutineScope()

    if ( isLoading ) {
        Box(
            Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            EmailField(email) { viewModel.onLoginChange(it, password) }
            PasswordField(password) { viewModel.onLoginChange(email, it) }
            Register(navController, mode)
            LoginButton(loginEnable) {
                coRoutineScope.launch {
                    viewModel.onLoginSelected() {
                        viewModel.signInWithEmailAndPassword(email, password) {
                            navController.navigate(route = NavScreenDir.Main.route)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(email: String, onTextFieldChanger : (String) -> Unit) {

    TextField(
        value = email,
        onValueChange = { onTextFieldChanger(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(password : String, onTextFieldChanger: (String) -> Unit) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column (
        horizontalAlignment = Alignment.End
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { onTextFieldChanger(it) },
            placeholder = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            maxLines = 1,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                focusedIndicatorColor = Color.Red,
                unfocusedIndicatorColor = Color.Blue
            )
        )
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = passwordVisible,
                onCheckedChange = { passwordVisible = !passwordVisible }
            )
            Text(text = "Mostrar contraseña.")
        }
    }
}

@Composable
fun Register(navController: NavController, mode : MutableSet<Boolean>) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "¿No tienes cuenta?",
            modifier = Modifier,
            fontSize = 16.sp,
            color = Color.Gray
        )
        Text(
            text = "Registrate",
            modifier = Modifier.clickable { navController.navigate(NavScreenDir.Register.route) },
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Blue
        )
    }
}

@Composable
fun LoginButton(loginEnable: Boolean, onLoginSelected : () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        enabled = loginEnable,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        )
    ) {
        Text(
            text = "Log-In",
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}