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
import retrofit2.http.Query

interface ApiService {
    // Account
    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest, @Query("isBio") isBio: Boolean): Response<ApiResponse>

    @POST("/auth/register")
    suspend fun registerCustomer(@Body request: CusRegisterRequest): Response<ApiResponse>

    @POST("/auth/register")
    suspend fun registerModerator(@Body request: ModRegisterRequest): Response<ApiResponse>

    // Amenity
    @GET("/{role}/amenity")
    suspend fun getAmenities(@Path("role") role: String): Response<ApiResponse>

    // Hotel and Room
    @GET("/{role}/hotel/all")
    suspend fun getHotels(@Path("role") role: String): Response<ApiResponse>

    @GET("/{role}/hotel/rating/{hotelId}")
    suspend fun getAvaregeRating(@Path("role") role: String, @Path("hotelId") hotelId: String): Response<ApiResponse>

    @GET("/{role}/hotel/price/{hotelId}")
    suspend fun getPriceRange(@Path("role") role: String, @Path("hotelId") hotelId: String): Response<ApiResponse>
}