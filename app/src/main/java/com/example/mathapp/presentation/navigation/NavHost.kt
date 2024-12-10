package com.example.mathapp.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mathapp.R
import com.example.mathapp.app.host.SessionViewModel
import com.example.mathapp.presentation.common.sharedComponents.ScreenContent
import com.example.mathapp.presentation.common.sharedComponents.TopBar
import com.example.mathapp.presentation.ui.about.AboutScreen
import com.example.mathapp.presentation.ui.auth.AuthScreen
import com.example.mathapp.presentation.ui.book.BookScreen
import com.example.mathapp.presentation.ui.docs.DocsScreen
import com.example.mathapp.presentation.ui.home.HomeScreen
import com.example.mathapp.presentation.ui.settings.SettingsScreen
import kotlinx.coroutines.launch

@Composable
fun NavHost(
    navController: NavHostController,
    sessionViewModel: SessionViewModel = viewModel()
) {
    val context = LocalContext.current

    val isSessionValid = sessionViewModel.isSessionValid.collectAsState().value

    sessionViewModel.checkSession(context)

    NavHost(
        navController = navController,
        startDestination = if (isSessionValid) ScreensRoute.HomeScreen.name else ScreensRoute.AuthScreen.name
    ) {
        composable(ScreensRoute.HomeScreen.name) {
            ScreenContent( { HomeScreen(navController) } )
        }
        composable(ScreensRoute.BookScreen.name) {
            ScreenContent( { BookScreen() } )
        }
        composable(ScreensRoute.AuthScreen.name) {
            ScreenContent( { AuthScreen(navController) } )
        }
        composable(ScreensRoute.SettingsScreen.name) {
            ScreenContent( { SettingsScreen() } )
        }
        composable(ScreensRoute.DocsScreen.name) {
            ScreenContent( { DocsScreen() } )
        }
        composable(ScreensRoute.AboutScreen.name) {
            ScreenContent( { AboutScreen() } )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DrawerNavigationScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""

    val title = when (currentRoute) {
        ScreensRoute.HomeScreen.name -> stringResource(id = R.string.screen_home_2)
        ScreensRoute.BookScreen.name -> stringResource(id = R.string.screen_book)
        ScreensRoute.AuthScreen.name -> stringResource(id = R.string.screen_auth)
        ScreensRoute.SettingsScreen.name -> stringResource(id = R.string.screen_settings)
        ScreensRoute.DocsScreen.name -> stringResource(id = R.string.screen_docs)
        ScreensRoute.AboutScreen.name -> stringResource(id = R.string.screen_about)
        else -> stringResource(id = R.string.app_name)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = currentRoute != ScreensRoute.AuthScreen.name,
        drawerContent = {
            MenuBody(
                menuItems = drawerScreens,
                drawerState = drawerState,
                scope = scope
            ) {
                navController.navigate(it.id.name) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                if (currentRoute != ScreensRoute.AuthScreen.name) {
                    TopBar(
                        title = title,
                        openDrawer = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier.padding(it)
            ) {
                NavHost(navController = navController)
            }
        }
    }
}