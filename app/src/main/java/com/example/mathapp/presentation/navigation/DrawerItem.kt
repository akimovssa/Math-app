package com.example.mathapp.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mathapp.R
import com.example.mathapp.app.host.SupabaseClientConn
import com.example.mathapp.app.host.UserPreferences
import com.example.mathapp.app.host.UserViewModel
import io.github.jan.supabase.auth.auth

@Composable
fun DrawerHeader() {
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    val client = SupabaseClientConn.getClient()
    val session = client.auth.currentSessionOrNull()
    val user = session?.user

    val userViewModel: UserViewModel = viewModel()

    LaunchedEffect(user) {
        if (user != null) {
            val userId = user.id
            val userEmail = user.email ?: ""

            userViewModel.updateUserData(userId, userEmail)

            UserPreferences.saveUserData(context = context, userId = userId, userEmail = userEmail)
        }
    }

    val userId = userViewModel.userId.value
    val userEmail = userViewModel.userEmail.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 15.dp, 10.dp, 0.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_profile),
                contentDescription = "Profile",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(70.dp)
            )
            Text(
                text = userEmail,
                color = colorResource(R.color.whitesmoke),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }

        Button(
            modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, 0.dp),
            colors = ButtonDefaults.buttonColors(
                Color.Transparent
            ),
            onClick = {
                clipboardManager.setText(AnnotatedString(userId))
            },
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "UUID: $userId",
                color = colorResource(R.color.whitesmoke),
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun DrawerItem(menuItem: MenuItem, modifier: Modifier = Modifier, onItemClick: (MenuItem) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.whitesmoke))
            .clickable {
                onItemClick(menuItem)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(10.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(menuItem.icon),
                contentDescription = stringResource(menuItem.textId)
            )
            Text(
                text = stringResource(menuItem.textId),
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
        }
        HorizontalDivider()
    }
}

@Composable
fun DrawerFooter() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Версия: 0.1",
            color = colorResource(R.color.white_200),
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}