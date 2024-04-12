package com.example.bookingapp.services

import com.example.bookingapp.models.Amenity
import com.example.bookingapp.models.ApiResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AmenityService {
    private val apiService: ApiService by lazy {
        RetrofitClient.apiService
    }

    suspend fun getAmenities(): List<Amenity> {
        val response = apiService.getAmenities()
        val apiResponse = response.body() as ApiResponse

        return Gson().fromJson(
            Gson().toJson(apiResponse.data),
            object : TypeToken<List<Amenity>>() {}.type
        )
    }
}