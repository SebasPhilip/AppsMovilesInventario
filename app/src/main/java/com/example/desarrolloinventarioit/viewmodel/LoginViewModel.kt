package com.example.desarrolloinventarioit.viewmodel

import androidx.lifecycle.ViewModel
import com.example.desarrolloinventarioit.model.DatosLogin
import com.example.desarrolloinventarioit.model.LoginErrores
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _datosLogin = MutableStateFlow(DatosLogin())
    val datosLogin: StateFlow<DatosLogin> = _datosLogin.asStateFlow()

    private val _loginErrores = MutableStateFlow(LoginErrores())
    val loginErrores: StateFlow<LoginErrores> = _loginErrores.asStateFlow()

    fun ingresarCorreo(nuevoCorreo: String) {
        _datosLogin.update { it.copy(correo = nuevoCorreo) }
        validarCorreo(nuevoCorreo)
    }

    fun ingresarContrasena(nuevaContrasena: String) {
        _datosLogin.update { it.copy(contrasena = nuevaContrasena) }
        validarContrasena(nuevaContrasena)
    }

    fun validarCorreo(correo: String) {
        val valido = android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()

        _loginErrores.update {
            it.copy(
                errorCorreo = if (valido || correo.isEmpty()) {
                    null
                } else {
                    "el correo no es válido"
                }
            )
        }
    }

    fun validarContrasena(contrasena: String) {
        val valido = contrasena.length >= 6
        _loginErrores.update {
            it.copy(
                errorContrasena = if (valido || contrasena.isEmpty()) {
                    null

                } else {
                    "La contrasaña no es válida"
                }
            )

        }

    }

    fun iniciarSesion (){
        val credencialesValidadas = _datosLogin.value

    }


}

