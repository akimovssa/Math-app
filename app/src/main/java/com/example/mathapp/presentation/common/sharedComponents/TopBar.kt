package com.example.mathapp.presentation.common.sharedComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mathapp.presentation.navigation.ScreensRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit
) {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""

    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.W500,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 40.dp, 0.dp),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            if (currentRoute != ScreensRoute.HiScreen.name) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .clickable { openDrawer() },
                    contentDescription = null
                )
            }
        },
        modifier = modifier

    )
}