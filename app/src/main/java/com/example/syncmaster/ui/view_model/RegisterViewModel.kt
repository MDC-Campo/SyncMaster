package com.example.syncmaster.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncmaster.data.database.dao.DeviceDao
import com.example.syncmaster.data.database.entities.DeviceEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val deviceDao: DeviceDao,
) : ViewModel() {

    val userName = MutableStateFlow("")
    val model = MutableStateFlow("")
    val phoneId = MutableStateFlow("")
    val registerEnable = MutableStateFlow(false)

    //Crear un Estado para los Mensajes de Éxito y Error:
    val messageState = MutableStateFlow<String?>(null)

    //Agregar estado para controlar la redireccion
    val isDataSaved = MutableStateFlow(false)

    // Función para restablecer el estado de isDataSaved
    fun resetDataSavedState() {
        isDataSaved.value = false
    }

    //Creamos una funcion de verificacion
    private fun isValidPhoneId(phoneId: String): Boolean = phoneId.length >= 15

    fun onRegisterChange(userName: String, model: String, phoneId: String) {
        // Actualizar los valores de StateFlow
        this.userName.value = userName
        this.model.value = model
        this.phoneId.value = phoneId

        // Verificar si todos los campos están llenos
        val allFieldsFilled = userName.isNotEmpty() && model.isNotEmpty() && phoneId.isNotEmpty()
        // Verificar si phoneId es válido
        val phoneIdValid = isValidPhoneId(phoneId)

        //Actualiza habilitacion del boton
        registerEnable.value = allFieldsFilled && phoneIdValid
    }


    fun saveData() {
        // Obtener los valores de StateFlow
        val userName = userName.value
        val model = model.value
        val phoneId = phoneId.value

        //Crear la entidad DeviceEntity
        val device = DeviceEntity(phoneId = phoneId, userName = userName, model = model)

        //Guardar la entidad en la BD
        viewModelScope.launch {
            try {
                deviceDao.insertDevice(device)
                messageState.value = "Datos guardados"
                isDataSaved.value = true //Actualiza el estado cuando los datos sean guardados

            } catch (e: Exception) {
                messageState.value = "Error al guardar los datos: ${e.message}"
            }
        }
    }
}