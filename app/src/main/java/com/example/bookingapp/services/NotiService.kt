package com.example.bookingapp.services

import com.example.bookingapp.models.ApiResponse
import com.example.bookingapp.models.Notification
import com.example.bookingapp.view_models.MainViewModel.authViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object NotiService {
    private val apiService: ApiService by lazy {
        RetrofitClient.apiService
    }

    suspend fun getNoti(): List<Notification> {
        try {
            val response = apiService.getNoti(authViewModel.account.role, authViewModel.account._id)
            val statusCode = response.code()

            return if (statusCode == 200) {
                val apiResponse = response.body() as ApiResponse

                Gson().fromJson(
                    Gson().toJson(apiResponse.data),
                    object: TypeToken<List<Notification>>() {}.type
                )

            } else {
                throw Exception("NotiService: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            throw Exception("NotiService: ${e.message}")
        }
    }
}