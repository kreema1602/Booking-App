package com.example.bookingapp.services

import android.content.Context
import android.util.Log
import com.example.bookingapp.models.Account
import com.example.bookingapp.models.ApiResponse
import com.example.bookingapp.models.requests.LoginRequest
import com.google.gson.Gson
import org.json.JSONObject

object AccountService {
    private val apiService: ApiService by lazy {
        RetrofitClient.apiService
    }

    suspend fun login(username: String, password: String, context: Context): Boolean {
        try {
            val request = LoginRequest(username, password)
            val response = apiService.login(request)
            val apiResponse = response.body() as ApiResponse

            return if (response.isSuccessful) {
                val jsonData = JSONObject(Gson().toJson(apiResponse.data))

                val account = Gson().fromJson(
                    jsonData.getJSONObject("account").toString(),
                    Account::class.java
                )
                val token = jsonData.getString("token")

                RetrofitClient.setAuthToken(token)
                saveAccount(account, token, context)
                Log.d("Test", getAccount(context).toString())

                true
            } else {
                RetrofitClient.clearAuthToken()
                false
            }
        } catch (e: Exception) {
            throw Exception("Failed to login: ${e.message}")
        }
    }

    private fun saveAccount(account: Account, token: String, context: Context) {
        val accountJson = Gson().toJson(account)

        val accountPref = context.getSharedPreferences("account", Context.MODE_PRIVATE)
        val accountEditor = accountPref.edit()
        accountEditor.putString("account", accountJson)
        accountEditor.apply()

        val tokenPref = context.getSharedPreferences("token", Context.MODE_PRIVATE)
        val tokenEditor = tokenPref.edit()
        tokenEditor.putString("token", token)
        tokenEditor.apply()

        val rolePref = context.getSharedPreferences("role", Context.MODE_PRIVATE)
        val roleEditor = rolePref.edit()
        roleEditor.putString("role", account.role)
        roleEditor.apply()
    }

    private fun getAccount(context: Context): Account? {
        val accountPref = context.getSharedPreferences("account", Context.MODE_PRIVATE)
        val accountJson = accountPref.getString("account", null)
        return if (accountJson != null) {
            Gson().fromJson(accountJson, Account::class.java)
        } else {
            null
        }
    }
}