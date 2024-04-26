package com.example.bookingapp.models.requests

data class ModRegisterRequest  (
    override val username: String,
    override val password: String,
    val hotel_name: String,
    val hotel_address: String,
    val description: String,
    val role: String
) : RegisterRequest