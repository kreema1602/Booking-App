package com.example.bookingapp.models

import java.io.Serializable

data class RoomFullDetail(
    val _id: String,
    val hotel: String,
    val name: String,
    val isAccepted: Boolean,
    val amenitiesIds: List<String>,
    val image: List<String>,
    val isBooked: Boolean,
    val roomType: String,
    val description: String,
    val price: Int,
    val guest: Int,
    val bedroom: Int,
    val bathroom: Int,
    val area: Int
): Serializable {
    constructor() : this("", "", "", false, listOf(), listOf(), false, "", "", 0, 0, 0, 0, 0)
}