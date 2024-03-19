package com.example.bookingapp.models;

data class ReservationItem(
    val id: Int, // New ID field
    val name: String,
    val status: String,
    val date: String,
    val price: String,
    val roomType: String,
    val imageResource: List<Int>
)