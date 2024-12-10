package com.example.mathapp.presentation.ui.home.components

import android.app.Activity
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material3.Text
import com.example.mathapp.R
import com.example.mathapp.app.host.SupabaseClientConn
import com.example.mathapp.presentation.navigation.ScreensRoute
import com.example.mathapp.presentation.ui.home.HomePageItem
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.SignOutScope
import io.github.jan.supabase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomePageDraw(
    pageItems: List<HomePageItem>,
    scope: CoroutineScope,
    onItemClick: (ScreensRoute) -> Unit
) {
    val client = SupabaseClientConn.getClient()
    val session = client.auth.currentSessionOrNull()
    val user = session?.user

    var width by remember { mutableStateOf(0) }
    var isDialogVisible by remember { mutableStateOf(false) }
    val activity = LocalContext.current as Activity

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

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        isDialogVisible = true
                    }
                    .onGloballyPositioned { layoutCoordinates ->
                        width = layoutCoordinates.size.width / 3
                    }
                    .height(width.dp)
                    .background(colorResource(R.color.red_75), RoundedCornerShape(15.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_logout),
                    tint = colorResource(R.color.whitesmoke),
                    modifier = Modifier
                        .size(60.dp),
                    contentDescription = null
                )
            }
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
                .fillMaxWidth()
                .background(color = if (user == null ) colorResource(R.color.black_200) else colorResource(R.color.black_200), RoundedCornerShape(15.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (user == null ) "Письмо для подтверждения отправлено на указаную почту!" else "Авторизован",
                fontSize = if (user == null ) 18.sp else 48.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W600,
                color = if (user == null ) colorResource(R.color.white_200) else colorResource(R.color.white_200)
            )
        }
    }

    if (isDialogVisible) {
        LogOutDialog(
            scope = scope,
            activity = activity,
            client = client,
            onItemClick = onItemClick,
            dialogTitle = "Выход из аккаунта",
            dialogText = "Вы уверены, что хотите выйти?",
            icon = ImageVector.vectorResource(R.drawable.ic_logout),
            onDismissRequest = { isDialogVisible = false },
            onConfirmation = { isDialogVisible = false }
        )
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

@Composable
fun LogOutDialog(
    scope: CoroutineScope,
    activity: Activity,
    onItemClick: (ScreensRoute) -> Unit,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    client: SupabaseClient,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        modifier = Modifier
            .fillMaxWidth(),
        icon = {
            Icon(
                icon,
                tint = colorResource(R.color.black_100),
                contentDescription = "Log out")
        },
        title = {
            Text(
                dialogTitle,
                fontSize = 24.sp,
                color = colorResource(R.color.black_100)
            )
        },
        text = {
            Text(
                dialogText,
                fontSize = 16.sp,
                color = colorResource(R.color.black_100)
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    activity.recreate()
                    scope.launch {
                        client.auth.signOut(SignOutScope.GLOBAL)
                    }
                    onItemClick(ScreensRoute.AuthScreen)
                    onConfirmation()
                }
            ) {
                Text(
                    "Подтвердить",
                    fontSize = 16.sp,
                    color = colorResource(R.color.red_100)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                },
            ) {
                Text(
                    "Отменить",
                    fontSize = 16.sp,
                    color = colorResource(R.color.black_100)
                )
            }
        }
    )
}