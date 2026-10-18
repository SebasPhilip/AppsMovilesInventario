package com.example.desarrolloinventarioit.ui.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.desarrolloinventarioit.viewmodel.LoginViewModel


@Composable
fun pantallaLogin(viewModel: LoginViewModel) {

    val datosLogin by viewModel.datosLogin.collectAsState()
    val loginErrores by viewModel.loginErrores.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = datosLogin.correo,
            onValueChange = {
               viewModel.ingresarCorreo(it)
            },
            label = { Text("Correo") },
            isError = loginErrores.errorCorreo != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (loginErrores.errorCorreo !=null) {
            Text("El correo ingresado no es válido", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = datosLogin.contrasena,
            onValueChange = {
                viewModel.ingresarContrasena(it)
            },
            label = { Text("Contraseña") },
            isError = loginErrores.errorContrasena!=null,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        if (loginErrores.errorContrasena!=null) {
            Text("La contraseña debe contener al menos 6 carácteres", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.iniciarSesion() },
            enabled = loginErrores.errorCorreo == null &&
            loginErrores.errorContrasena==null &&
            datosLogin.correo.isNotEmpty()&&
            datosLogin.correo.isNotEmpty()
        ) {
            Text("Iniciar sesión")
        }
    }
}



