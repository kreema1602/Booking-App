package com.example.bookingapp.models.requests

data class VerifyOTPRequest(
    val username: String,
    val otp: String
)