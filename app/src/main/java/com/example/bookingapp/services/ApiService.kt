package com.example.bookingapp.services

import com.example.bookingapp.models.ApiResponse
import com.example.bookingapp.models.requests.CusRegisterRequest
import com.example.bookingapp.models.requests.LoginRequest
import com.example.bookingapp.models.requests.ModRegisterRequest
import com.example.bookingapp.models.requests.VerifyOTPRequest
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

    @POST("/auth/forgot-password")
    suspend fun forgotPassword(@Body request: LoginRequest): Response<ApiResponse>

    @POST("/auth/reset-password")
    suspend fun resetPassword(@Body request: LoginRequest): Response<ApiResponse>

    @POST("/auth/verify-otp")
    suspend fun verifyOTP(@Body request: VerifyOTPRequest): Response<ApiResponse>

    // Amenity
    @GET("/{role}/amenity")
    suspend fun getAmenities(@Path("role") role: String): Response<ApiResponse>

    @GET("/{role}/hotel/amenity/{hotelId}")
    suspend fun getHotelAmenities(@Path("role") role: String, @Path("hotelId") hotelId: String): Response<ApiResponse>

    // Hotel and Room
    @GET("/{role}/hotel")
    suspend fun getHotels(@Path("role") role: String, @Query("start") start: Int, @Query("number") num: Int): Response<ApiResponse>

    @GET("/{role}/hotel/{hotelId}")
    suspend fun getHotel(@Path("role") role: String, @Path("hotelId") hotelId: String): Response<ApiResponse>

    @GET("/{role}/hotel/rating/{hotelId}")
    suspend fun getAverageRating(@Path("role") role: String, @Path("hotelId") hotelId: String): Response<ApiResponse>

    @GET("/{role}/hotel/price/{hotelId}")
    suspend fun getPriceRange(@Path("role") role: String, @Path("hotelId") hotelId: String): Response<ApiResponse>

    @GET("/{role}/room/{hotelId}?is_full_detail=true")
    suspend fun getRoomFullDetail(@Path("role") role: String, @Path("hotelId") hotelId: String): Response<ApiResponse>
}