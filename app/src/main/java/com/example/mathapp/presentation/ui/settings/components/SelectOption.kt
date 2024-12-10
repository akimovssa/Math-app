package com.example.mathapp.presentation.ui.settings.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mathapp.R

@Composable
fun SelectOption() {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val languages = listOf("Русский")
    val themes = listOf("Светлая")

    var selectedLanguage by remember { mutableStateOf(languages[0]) }
    var selectedTheme by remember { mutableStateOf(themes[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(5.dp, 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomDropdownMenu(
            title = "Выбор языка",
            items = languages,
            selectedItem = selectedLanguage,
            onItemSelected = { item ->
                selectedLanguage = item
                Toast.makeText(context, "Выбран язык: $item", Toast.LENGTH_SHORT).show()
            }
        )

        Spacer(modifier = Modifier.height(0.dp))

        CustomDropdownMenu(
            title = "Выбор темы",
            items = themes,
            selectedItem = selectedTheme,
            onItemSelected = { item ->
                selectedTheme = item
                Toast.makeText(context, "Выбрана тема: $item", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdownMenu(
    title: String,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Text(
            text = title,
            fontWeight = FontWeight.W600,
            fontSize = 16.sp,
            modifier = Modifier.padding(15.dp, 0.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedItem,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
                    .padding(0.dp, 5.dp),
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(colorResource(R.color.whitesmoke))
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                fontWeight = FontWeight.W500,
                                fontSize = 14.sp,
                                color = colorResource(R.color.black_100)
                            )
                        },
                        onClick = {
                            onItemSelected(item)
                            expanded = false
                        },
                        modifier = Modifier.fillMaxWidth().background(colorResource(R.color.whitesmoke))
                    )
                }
            }
        }
    }
}