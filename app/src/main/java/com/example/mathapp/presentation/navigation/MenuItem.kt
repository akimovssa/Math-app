package com.example.mathapp.presentation.navigation

import com.example.mathapp.R

data class MenuItem(
    val id: ScreensRoute,
    val icon: Int,
    val textId: Int
)

val drawerScreens = listOf(
    MenuItem(ScreensRoute.HomeScreen, R.drawable.ic_home, R.string.screen_home_2),
    MenuItem(ScreensRoute.SettingsScreen, R.drawable.ic_settings, R.string.screen_settings),
    MenuItem(ScreensRoute.DocsScreen, R.drawable.ic_book_mini, R.string.screen_docs),
    MenuItem(ScreensRoute.AboutScreen, R.drawable.ic_info, R.string.screen_about),
)

enum class ScreensRoute {
    HomeScreen, BookScreen, TestingScreen, HiScreen, SettingsScreen, DocsScreen, AboutScreen
}