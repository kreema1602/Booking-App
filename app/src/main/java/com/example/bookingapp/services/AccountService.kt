package com.example.bookingapp.services

import android.content.Context
import android.util.Log
import com.example.bookingapp.MainActivity
import com.example.bookingapp.models.Account
import com.example.bookingapp.models.ApiResponse
import com.example.bookingapp.models.requests.LoginRequest
import com.example.bookingapp.view_models.MainViewModel
import com.google.gson.Gson
import org.json.JSONObject

object AccountService {
    private val apiService: ApiService by lazy {
        RetrofitClient.apiService
    }

    suspend fun login(username: String, password: String): Pair<Account, String>? {
        try {
            val request = LoginRequest(username, password)
            val response = apiService.login(request)
            val statusCode = response.code()

            return if (statusCode == 200) {
                val apiResponse = response.body() as ApiResponse
                val jsonData = JSONObject(Gson().toJson(apiResponse.data))

                val account = Gson().fromJson(
                    jsonData.getJSONObject("account").toString(),
                    Account::class.java
                )
                val token = jsonData.getString("token")

                // return both account and token
                Pair(account, token)
            } else {
                RetrofitClient.clearAuthToken()
                null
            }
        } catch (e: Exception) {
            throw Exception("Failed to login: ${e.message}")
        }
    }
}