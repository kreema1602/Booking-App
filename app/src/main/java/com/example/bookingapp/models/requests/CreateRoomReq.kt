package com.example.bookingapp.models.requests

data class CreateRoomReq(
    val hotelName: String,
    val roomType: String,
    val roomName: String,
    val isAccepted: Boolean,
    val isBooked: Boolean,
    val img: String,
    val amenity: List<String>
)