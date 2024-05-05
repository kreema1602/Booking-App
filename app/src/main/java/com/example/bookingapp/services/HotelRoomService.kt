package com.example.bookingapp.services

import android.util.Log
import com.example.bookingapp.models.Account
import com.example.bookingapp.models.ApiResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import retrofit2.Response

object HotelRoomService {
    private val apiService: ApiService by lazy {
        RetrofitClient.apiService
    }

    // generate gson with lazy initialization to avoid memory leak
    private val gson: Gson by lazy {
        Gson()
    }

    suspend fun getHotels(role: String): List<Account> {
        try {
            val response = apiService.getHotels(role)
            return processResponse(response) {
                RetrofitClient.clearAuthToken()
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }

    suspend fun getAvaregeRating(role: String, hotelId: String): Double {
        try {
            val response = apiService.getAvaregeRating(role, hotelId)
            return processResponse(response) {
                RetrofitClient.clearAuthToken()
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }

    suspend fun getPriceRange(role: String, hotelId: String) : Pair<Double, Double> {
        try {
            val response = apiService.getPriceRange(role, hotelId)
            val statusCode = response.code()
            return if (statusCode == 200) {
                val apiResponse = response.body() as ApiResponse
                val jsonData = JSONObject(apiResponse.data.toString())
                Log.d("HotelRoomService", "getPriceRange: $jsonData")
                val minPrice = jsonData.getString("min").toDouble()
                val maxPrice = jsonData.getString("max").toDouble()

                Pair(minPrice, maxPrice)
            } else {
                val errorBody = response.errorBody()?.string() ?: throw Exception("No error body")
                val errorResponse = gson.fromJson(errorBody, ApiResponse::class.java)

                throw Exception(errorResponse.message)
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }

    private inline fun <reified T> processResponse(response: Response<ApiResponse>, optionFun: () -> Unit = {}): T {
        val statusCode = response.code()

        return if (statusCode == 200) {
            val apiResponse = response.body() ?: throw Exception("No response body")

            gson.fromJson(
                gson.toJson(apiResponse.data),
                object : TypeToken<T>() {}.type
            )
        } else {
            optionFun()
            val errorBody = response.errorBody()?.string() ?: throw Exception("No error body")
            val errorResponse = gson.fromJson(errorBody, ApiResponse::class.java)

            throw Exception(errorResponse.message)
        }
    }
}