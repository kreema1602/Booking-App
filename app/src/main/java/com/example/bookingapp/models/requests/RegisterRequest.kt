package com.example.bookingapp.models.requests

data class CusRegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val phone: String,
    val fullname: String,
    val role: String
)

data class ModRegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val phone: String,
    val fullname: String,
    val hotelName: String,
    val hotelAddress: String,
    val description: String,
    val role: String
)