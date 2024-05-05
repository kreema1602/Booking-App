package com.example.bookingapp.models

data class Booking(
    val _id: String,
    val hotel: String,
    val room: String,
    val customer: String,
    val checkIn: String,
    val checkOut: String,
    val isCanceled: Boolean,
    val status: String,
    val createdAt: String,
    val updatedAt: String
) {
    constructor() : this("", "", "", "", "", "", false, "", "", "")
}