package com.example.mathapp.presentation.ui.auth

import android.app.Activity
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mathapp.app.host.SupabaseClientConn
import com.example.mathapp.presentation.navigation.ScreensRoute
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val client = SupabaseClientConn.getClient()

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> get() = _uiState

    private val _shouldRecreateActivity = MutableStateFlow(false)
    val shouldRecreateActivity: StateFlow<Boolean> get() = _shouldRecreateActivity

    fun register(userEmail: String, userPassword: String, navController: NavHostController) {
        viewModelScope.launch {
            try {
                client.auth.signUpWith(Email) {
                    email = userEmail
                    password = userPassword
                }
                _uiState.value = AuthUiState.Success("Регистрация прошла успешно!")

                navController.navigate(ScreensRoute.HomeScreen.name) {
                    popUpTo(ScreensRoute.AuthScreen.name) { inclusive = true }
                }

                _shouldRecreateActivity.value = true
            } catch (e: Exception) {
                _uiState.value = AuthUiState.Error(e.localizedMessage ?: "Ошибка регистрации")
            }
        }
    }

    fun login(userEmail: String, userPassword: String, navController: NavController) {
        viewModelScope.launch {
            try {
                client.auth.signInWith(Email) {
                    email = userEmail
                    password = userPassword
                }
                _uiState.value = AuthUiState.Success("Авторизация успешна!")

                navController.navigate(ScreensRoute.HomeScreen.name) {
                    popUpTo(ScreensRoute.AuthScreen.name) { inclusive = true }
                }

                _shouldRecreateActivity.value = true
            } catch (e: Exception) {
                _uiState.value = AuthUiState.Error(e.localizedMessage ?: "Ошибка авторизации")
            }
        }
    }

    fun resetRecreateFlag() {
        _shouldRecreateActivity.value = false
    }
}

sealed class AuthUiState {
    object Idle : AuthUiState()
    data class Success(val message: String) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}