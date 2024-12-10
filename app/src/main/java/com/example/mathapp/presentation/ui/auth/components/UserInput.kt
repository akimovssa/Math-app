package com.example.mathapp.presentation.ui.auth.components

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavHostController
import com.example.mathapp.R
import com.example.mathapp.presentation.ui.auth.AuthUiState
import com.example.mathapp.presentation.ui.auth.AuthViewModel

@Composable
fun AuthForm(
    viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController
) {
    val shouldRecreate by viewModel.shouldRecreateActivity.collectAsState(false)

    if (shouldRecreate) {
        (LocalContext.current as? Activity)?.recreate()
        viewModel.resetRecreateFlag()
    }

    val uiState by viewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isLogin by remember { mutableStateOf(true) }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text( "Почта" ,
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
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(
                    "Пароль",
                    color = colorResource(R.color.white_200),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600
                )
            },
            textStyle = TextStyle(
                color = colorResource(R.color.black_100),
                fontWeight = FontWeight.W600,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .background(colorResource(R.color.white_100), RoundedCornerShape(15.dp)),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off
                val description = if (passwordVisible) "Скрыть пароль" else "Показать пароль"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(image),
                        contentDescription = description,
                        tint = colorResource(R.color.black_100)
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.height(40.dp))

        if (!isLogin) {
            Button(
                onClick = { viewModel.login(email, password, navController) },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.green_100)),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    "Вход",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W800,
                    color = colorResource(R.color.black_100)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Зарегистрироваться",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                color = colorResource(R.color.black_100),
                modifier = Modifier
                    .clickable {
                        isLogin = !isLogin
                    }
            )
        } else {
            Button(
                onClick = { viewModel.register(email, password, navController) },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.green_100)),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    "Регистрация",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W800,
                    color = colorResource(R.color.black_100)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "Войти",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                color = colorResource(R.color.black_100),
                modifier = Modifier
                    .clickable {
                        isLogin = !isLogin
                    }
            )
        }
        Spacer(modifier = Modifier.height(72.dp))
        when (uiState) {
            is AuthUiState.Success -> Text(
                "Успешно: ${(uiState as AuthUiState.Success).message}",
                color = colorResource(R.color.green_100)
            )
            is AuthUiState.Error -> Text(
                "Ошибка: ${(uiState as AuthUiState.Error).message}",
                color = colorResource(R.color.red_100)
                )
            else -> {}
        }
    }
}