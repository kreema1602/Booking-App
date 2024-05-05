package com.example.bookingapp.services

import com.example.bookingapp.models.Amenity
import com.example.bookingapp.models.ApiResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AmenityService {
    private val apiService: ApiService by lazy {
        RetrofitClient.apiService
    }

    suspend fun getAmenities(role: String): List<Amenity> {
        return try {
            val response = apiService.getAmenities(role)
            val apiResponse = response.body() as ApiResponse

            Gson().fromJson(
                Gson().toJson(apiResponse.data),
                object : TypeToken<List<Amenity>>() {}.type
            )
        } catch (e: Exception) {
            throw Exception("Failed to get amenities: ${e.message}")
        }
    }
}