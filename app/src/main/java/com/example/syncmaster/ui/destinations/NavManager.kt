package com.example.syncmaster.ui.destinations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.syncmaster.ui.view.HomeScreen
import com.example.syncmaster.ui.view.RegisterScreen
import com.example.syncmaster.ui.view_model.RegisterViewModel

@Composable
fun NavManager(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "register_screen" ){
        composable("register_screen"){

           HomeScreen()
        }
        composable("home_screen"){
            //Crea la instancia de registerViewModel
            val viewModel: RegisterViewModel = hiltViewModel()
            //Llama a registerScreen y pasa el NavController
            RegisterScreen(viewModel = viewModel, navController= navController)
        }
    }
}