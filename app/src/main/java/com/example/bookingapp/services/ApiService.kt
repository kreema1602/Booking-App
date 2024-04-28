package com.example.bookingapp.services

import com.example.bookingapp.models.ApiResponse
import com.example.bookingapp.models.requests.CusRegisterRequest
import com.example.bookingapp.models.requests.LoginRequest
import com.example.bookingapp.models.requests.ModRegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    // Account
    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse>

    @POST("/auth/register")
    suspend fun register_customer(@Body request: CusRegisterRequest): Response<ApiResponse>

    @POST("/auth/register")
    suspend fun register_moderator(@Body request: ModRegisterRequest): Response<ApiResponse>

    // Amenity
    @GET("/{role}/amenity")
    suspend fun getAmenities(@Path("role") role: String): Response<ApiResponse>
}