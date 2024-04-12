package com.example.bookingapp.services

import com.example.bookingapp.CurrentAccount
import com.example.bookingapp.models.Account
import com.example.bookingapp.models.ApiResponse
import com.example.bookingapp.models.requests.LoginRequest
import com.google.gson.Gson
import org.json.JSONObject

object AccountService {
    private val apiService: ApiService by lazy {
        RetrofitClient.apiService
    }

    suspend fun login(username: String, password: String): Boolean {
        val request = LoginRequest(username, password)
        val response = apiService.login(request)
        val apiResponse = response.body() as ApiResponse

        return if (response.isSuccessful) {
            val jsonData = JSONObject(Gson().toJson(apiResponse.data))
            RetrofitClient.setAuthToken(jsonData.getString("token"))

            val account = Gson().fromJson(jsonData.getJSONObject("account").toString(), Account::class.java)
            CurrentAccount.setAccount(account)

            true
        } else {
            RetrofitClient.clearAuthToken()
            false
        }
    }
}