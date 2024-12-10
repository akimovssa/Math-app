package com.example.mathapp.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.mathapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MenuBody(
    menuItems: List<MenuItem>,
    drawerState: DrawerState,
    scope: CoroutineScope,
    modifier: Modifier = Modifier,
    onItemClick: (MenuItem) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(.75f)
            .background(colorResource(R.color.whitesmoke))
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(.25f)
                .fillMaxWidth()
                .background(colorResource(R.color.black_100))
        ) {
            DrawerHeader()
        }
        LazyColumn {
            items(menuItems) { item ->
                DrawerItem(
                    item,
                    modifier = modifier
                ) {
                    scope.launch {
                        drawerState.close()
                    }
                    onItemClick(item)
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Column {
            DrawerFooter()
        }
    }
}