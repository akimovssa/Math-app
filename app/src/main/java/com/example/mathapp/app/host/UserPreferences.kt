package com.example.mathapp.app.host

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

object UserPreferences {
    private const val PREFERENCES_NAME = "user_prefs"
    private const val USER_ID_KEY = "user_id"
    private const val USER_EMAIL_KEY = "user_email"

    fun saveUserData(context: Context, userId: String, userEmail: String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putString(USER_ID_KEY, userId)
            .putString(USER_EMAIL_KEY, userEmail)
            .apply()
    }

    fun getUserData(context: Context): Pair<String?, String?> {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString(USER_ID_KEY, null)
        val userEmail = sharedPreferences.getString(USER_EMAIL_KEY, null)
        return Pair(userId, userEmail)
    }

    fun clearUserData(context: Context) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }
}

class SessionViewModel : ViewModel() {
    private val _isSessionValid = MutableStateFlow(false)
    val isSessionValid: StateFlow<Boolean> = _isSessionValid

    fun checkSession(context: Context) {
        viewModelScope.launch {
            _isSessionValid.value = try {
                val client = SupabaseClientConn.getClient()
                val session = client.auth.currentSessionOrNull()
                val user = session?.user

                val (savedUserId, savedUserEmail) = UserPreferences.getUserData(context)

                if (user != null) {
                    val currentUserId = user.id
                    val currentUserEmail = user.email ?: ""

                    if (currentUserId != savedUserId || currentUserEmail != savedUserEmail) {
                        UserPreferences.saveUserData(context, currentUserId, currentUserEmail)
                    }
                    true
                } else {
                    UserPreferences.clearUserData(context)
                    false
                }
            } catch (e: Exception) {
                Log.e("SessionData", "Ошибка проверки сессии: ${e.localizedMessage}")
                UserPreferences.clearUserData(context)
                false
            }
        }
    }
}

class UserViewModel : ViewModel() {
    var userId = mutableStateOf<String>("")
    var userEmail = mutableStateOf<String>("")

    fun updateUserData(id: String, email: String) {
        userId.value = id
        userEmail.value = email
    }
}

