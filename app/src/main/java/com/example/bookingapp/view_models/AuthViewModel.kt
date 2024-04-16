package com.example.bookingapp.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bookingapp.models.Account
import com.example.bookingapp.services.AccountService

class AuthViewModel: ViewModel() {
    var isAuthenticated by mutableStateOf(false)
        private set
    var authToken by mutableStateOf("")
        private set
    var account by mutableStateOf(Account())
        private set

    suspend fun login(email: String, password: String): Boolean {
        try {
            AccountService.login(email, password).let {
                if (it != null) {
                    account = it.first
                    authToken = it.second
                    isAuthenticated = true
                    return true
                }
            }
        } catch (e: Exception) {
            throw Exception("Failed to login: ${e.message}")
        }

        return false
    }
}