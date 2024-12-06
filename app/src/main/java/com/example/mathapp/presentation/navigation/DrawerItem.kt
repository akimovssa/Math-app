package com.example.mathapp.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mathapp.R

@Composable
fun DrawerHeader() {
    val clipboardManager = LocalClipboardManager.current
    val uuid = "12345678-1234-1234-1234-123456789012"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp, 15.dp, 15.dp, 0.dp),
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
                text = "ФИО",
                color = colorResource(R.color.whitesmoke),
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
                clipboardManager.setText(AnnotatedString(uuid))
            },
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "UUID: $uuid",
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Версия 0.0.1",
            color = colorResource(R.color.white_200),
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}