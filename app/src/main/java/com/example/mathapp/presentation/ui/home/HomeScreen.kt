package com.example.mathapp.presentation.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mathapp.presentation.navigation.ScreensRoute

@Composable
fun HomeScreen(navController: NavController) {
    val onItemClick: (ScreensRoute) -> Unit = { screen ->
        navController.navigate(screen.name)
    }

    HomePageItems(onItemClick)
}