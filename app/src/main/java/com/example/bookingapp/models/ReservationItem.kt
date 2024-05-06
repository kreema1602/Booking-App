package com.example.bookingapp.models;

data class ReservationItem(
    val _id: String, // New ID field
    val hotel_id: String,
    val hotel: String,
    val room: String,
    val roomtype: String,
    val price: Double,
    val image: List<String>,
    val status: String,
    val customer: String,
    val check_in: String,
    val check_out: String,
    val is_canceled: Boolean
) {
    constructor() : this("", "", "", "", "", 0.0, emptyList(), "", "", "", "", false)
}