package com.example.syncmaster.ui.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.syncmaster.R
import com.example.syncmaster.ui.view_model.RegisterViewModel



@Composable
fun RegisterScreen(viewModel: RegisterViewModel, navController:NavController) {
    val context = LocalContext.current
    val isDataSaved by viewModel.isDataSaved.collectAsState()

    // Verifica si los datos se han guardado y navega a HomeScreen
    if (isDataSaved) {
        navController.navigate("home_screen") {
            popUpTo("register_screen") { inclusive = true }
        }
        viewModel.resetDataSavedState()
    }

    // Observa el estado de messageState
    val message: String? by viewModel.messageState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.matchParentSize()
        )
        Register(Modifier.align(Alignment.Center), viewModel)

        message?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            // Restablece el estado del mensaje después de mostrarlo
            viewModel.messageState.value = null
        }
    }
}


@Composable
fun Register(modifier: Modifier, viewModel: RegisterViewModel) {
    Column(modifier = modifier) {
        Welcome(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        FormContainer(Modifier.align(Alignment.CenterHorizontally), viewModel)
    }
}


@Composable
fun Welcome(modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = "Bienvenido a SyncMaster",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun FormContainer(modifier: Modifier, viewModel: RegisterViewModel) {
    // Observar los valores de StateFlow
    val userName: String by viewModel.userName.collectAsState()
    val model: String by viewModel.model.collectAsState()
    val phoneId: String by viewModel.phoneId.collectAsState()
    val registerEnable: Boolean by viewModel.registerEnable.collectAsState()

    Box(
        modifier = modifier
            .padding(16.dp)
            .background(Color(0x80000000), shape = RoundedCornerShape(20.dp))
            .padding(20.dp)

    ) {
        Column {
            UserNameField(userName) { viewModel.onRegisterChange(it, model, phoneId) }
            Spacer(modifier = Modifier.padding(4.dp))
            ModelField(model) { viewModel.onRegisterChange(userName, it, phoneId) }
            Spacer(modifier = Modifier.padding(4.dp))
            PhoneIdField(phoneId) { viewModel.onRegisterChange(userName, model, it) }
            Spacer(modifier = Modifier.padding(16.dp))
            SaveButton(registerEnable) { viewModel.saveData() }
        }
    }
}

@Composable
fun UserNameField(userName: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = userName,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Nombre de usuario") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1
        )
}


@Composable
fun ModelField(model: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = model,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Modelo: M25016XL") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1
        )
}


@Composable
fun PhoneIdField(phoneId: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = phoneId,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Numero unico del telefono") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        maxLines = 1
    )
}

@Composable
fun SaveButton(registerEnable: Boolean, saveData: () -> Unit) {
    Button(
        //Solo llamamos el metodo saveData, si registerEnable es True
        onClick = { if (registerEnable) saveData() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF00C535),
            disabledContainerColor = Color(0xFFBEBEBE),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), enabled = registerEnable
    ) {
        Text(text = "Guardar Datos")
    }
}

