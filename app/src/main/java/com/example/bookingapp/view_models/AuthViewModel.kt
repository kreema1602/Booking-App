package com.example.bookingapp.view_models

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookingapp.MainActivity
import com.example.bookingapp.models.Account
import com.example.bookingapp.services.AccountService
import com.google.gson.Gson

class AuthViewModel: ViewModel() {
    var isAuthenticated by mutableStateOf(false)
        private set
    var authToken by mutableStateOf("")
        private set
    var account by mutableStateOf(Account())
        private set

    fun loadAccount() {
        try {
            val context = MainActivity.context
            val token = context.getSharedPreferences("token", Context.MODE_PRIVATE)
                .getString("token", "")
            val accountJson = context.getSharedPreferences("account", Context.MODE_PRIVATE)
                .getString("account", "")

            if (token != "" && accountJson != "") {
                authToken = token!!
                account = Gson().fromJson(accountJson, Account::class.java)
                isAuthenticated = true
            }
        } catch (e: Exception) {
            throw Exception("Auth view model: ${e.message}")
        }
    }

    suspend fun login(email: String, password: String): Boolean {
        try {
            AccountService.login(email, password).let {
                if (it != null) {
                    account = it.first
                    authToken = it.second
                    isAuthenticated = true
                }
            }
        } catch (e: Exception) {
            throw Exception("Auth view model: ${e.message}")
        }

        return isAuthenticated
    }
}