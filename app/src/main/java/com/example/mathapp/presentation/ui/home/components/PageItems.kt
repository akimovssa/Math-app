package com.example.mathapp.presentation.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material3.Text
import com.example.mathapp.R
import com.example.mathapp.presentation.navigation.ScreensRoute
import com.example.mathapp.presentation.ui.home.HomePageItem

@Composable
fun HomePageDraw(
    pageItems: List<HomePageItem>,
    onItemClick: (ScreensRoute) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(15.dp, 10.dp, 15.dp, 15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalArrangement = Arrangement.spacedBy(115.dp),
        modifier = Modifier
            .wrapContentSize()
    ) {
        items(pageItems) { item ->
            Item(item, onItemClick)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box (
            modifier = Modifier
                .height(180.dp)
                .clickable {
                    onItemClick(ScreensRoute.HiScreen)
                }
                .fillMaxWidth()
                .background(colorResource(R.color.red_75), RoundedCornerShape(15.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "REG IN",
                fontSize = 48.sp,
                fontWeight = FontWeight.W600,
                color = colorResource(R.color.whitesmoke)
            )
        }
    }
}

@Composable
fun Item(
    pageItem: HomePageItem,
    onItemClick: (ScreensRoute) -> Unit
) {
    var width by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(pageItem.id)
            }
            .onGloballyPositioned { layoutCoordinates ->
                width = layoutCoordinates.size.width / 3
            }
            .height(width.dp)
            .background(pageItem.color, RoundedCornerShape(15.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(pageItem.icon),
            tint = pageItem.iconTint,
            contentDescription = null
        )
    }
}