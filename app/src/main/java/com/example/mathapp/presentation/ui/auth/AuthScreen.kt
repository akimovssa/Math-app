package com.example.mathapp.presentation.ui.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.mathapp.presentation.ui.auth.components.AuthForm

@Composable
fun AuthScreen(navController: NavHostController) {
    AuthForm(navController = navController)
}