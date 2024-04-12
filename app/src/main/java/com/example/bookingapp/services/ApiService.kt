package com.example.bookingapp.services

import com.example.bookingapp.models.ApiResponse
import com.example.bookingapp.models.requests.LoginRequest
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    // Account
    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse>

    // Amenity
//    @GET("/amenities")
}