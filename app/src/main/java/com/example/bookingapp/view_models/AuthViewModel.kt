package com.example.bookingapp.view_models

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bookingapp.MainActivity
import com.example.bookingapp.models.Account
import com.example.bookingapp.services.AccountService
import com.example.bookingapp.services.RetrofitClient
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

    fun logout() {
        try {
            isAuthenticated = false
            authToken = ""
            account = Account()

            clearAccount()
            RetrofitClient.clearAuthToken()
        } catch (e: Exception) {
            throw Exception("Auth view model: ${e.message}")
        }
    }

    suspend fun login(email: String, password: String, isBio: Boolean): Boolean {
        try {
            AccountService.login(email, password, isBio).let {
                if (it != null) {
                    account = it.first
                    authToken = it.second
                    isAuthenticated = true

                    saveAccount(account, authToken)
                    RetrofitClient.setAuthToken(authToken)
                }
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }

        return isAuthenticated
    }

    private fun saveAccount(account: Account, token: String) {
        val accountJson = Gson().toJson(account)
        val context = MainActivity.context

        val accountPref = context.getSharedPreferences("account", Context.MODE_PRIVATE)
        val accountEditor = accountPref.edit()
        accountEditor.putString("account", accountJson)
        accountEditor.apply()

        val tokenPref = context.getSharedPreferences("token", Context.MODE_PRIVATE)
        val tokenEditor = tokenPref.edit()
        tokenEditor.putString("token", token)
        tokenEditor.apply()
    }

    @SuppressLint("CommitPrefEdits")
    private fun clearAccount() {
        val context = MainActivity.context
        val tokenPref = context.getSharedPreferences("token", Context.MODE_PRIVATE)
        val tokenEditor = tokenPref.edit()
        tokenEditor.clear()
        tokenEditor.apply()
    }

    suspend fun register(fields: Map<String, String>, role: String): Boolean {
        try {
            return AccountService.register(fields, role)
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }

    // get username and password from shared preferences
    fun getCredentials(): Pair<String, String> {
        val context = MainActivity.context
        val accountJson = context.getSharedPreferences("account", Context.MODE_PRIVATE)
            .getString("account", "")
        val account = Gson().fromJson(accountJson, Account::class.java)

        return Pair(account.username, account.password)
    }
    suspend fun verifyOTP(username: String, otp: String): Boolean {
        try {
            return AccountService.verifyOTP(username, otp)
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }

    suspend fun forgotPassword(username: String): Boolean {
        try {
            return AccountService.forgotPassword(username)
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }

    suspend fun resetPassword(username: String, password: String): Boolean {
        try {
            return AccountService.resetPassword(username, password)
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }
}