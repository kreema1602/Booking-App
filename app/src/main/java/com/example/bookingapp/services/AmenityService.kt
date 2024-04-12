package com.example.bookingapp.services

import com.example.bookingapp.models.Amenity

object AmenityService {
    private val apiService: ApiService by lazy {
        RetrofitClient.apiService
    }

//    suspend fun getAmenities(): List<Amenity> {
//        val response = apiService.getAmenities()
//        return response.body() as List<Amenity>
//    }
}