package com.example.mathapp.presentation.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.mathapp.R
import com.example.mathapp.presentation.navigation.ScreensRoute
import com.example.mathapp.presentation.ui.home.components.HomePageDraw

data class HomePageItem(
    val id: ScreensRoute,
    val icon: Int,
    val iconTint: Color,
    val color: Color
)

@Composable
fun HomePageItems(onItemClick: (ScreensRoute) -> Unit) {
    val homePageItemsDrawer = listOf(
        HomePageItem(ScreensRoute.BookScreen, R.drawable.ic_book, colorResource(R.color.whitesmoke), colorResource(R.color.blue_75))
    )

    val scope = rememberCoroutineScope()

    HomePageDraw(homePageItemsDrawer, scope, onItemClick)
}