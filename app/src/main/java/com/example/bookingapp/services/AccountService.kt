package com.example.bookingapp.services

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

//        Log.d("AccountService", apiResponse.data.toString())

        if (response.isSuccessful) {
            val jsonData = JSONObject(Gson().toJson(apiResponse.data))
//            Log.d("AccountService", jsonData.getString("user"))
            RetrofitClient.setAuthToken(jsonData.getString("token"))
            return true
        } else {
            RetrofitClient.clearAuthToken()
            return false
        }
    }
}