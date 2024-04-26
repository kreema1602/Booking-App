package com.example.bookingapp.models.requests

data class CusRegisterRequest (
    override val username: String,
    override val password: String,
    val fullname: String,
    val role: String
) : RegisterRequest