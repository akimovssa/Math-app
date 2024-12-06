package com.example.mathapp.presentation.ui.hi.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.text.TextStyle
import com.example.mathapp.R

@Composable
fun UserInput() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp, 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(.15f))

        UserTextField("Фамилия*")
        Spacer(modifier = Modifier.height(15.dp))
        UserTextField("Имя*")
        Spacer(modifier = Modifier.height(15.dp))
        UserTextField("Отчество")

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {  },
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.green_100)),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                "Перейти",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                color = colorResource(R.color.black_100)
            )
        }

        Spacer(modifier = Modifier.weight(.5f))
    }
}

@Composable
fun UserTextField(
    label: String,
    //placeholder: String,
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(
            label,
            color = colorResource(R.color.white_200),
            fontSize = 16.sp,
            fontWeight = FontWeight.W600
        ) },
        textStyle = TextStyle(
            colorResource(R.color.black_100),
            fontWeight = FontWeight.W600,
            fontSize = 16.sp
        ),
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(colorResource(R.color.white_100), RoundedCornerShape(15.dp)),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun UserInputPreview() {
    UserInput()
}