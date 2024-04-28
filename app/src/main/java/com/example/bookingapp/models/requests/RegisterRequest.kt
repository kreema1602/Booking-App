package com.example.bookingapp.models.requests

data class CusRegisterRequest(
    val username: String,
    val password: String,
    val fullname: String,
    val role: String
)

data class ModRegisterRequest(
    val username: String,
    val password: String,
    val hotel_name: String,
    val hotel_address: String,
    val description: String,
    val role: String
)