package com.example.bookingapp.models

import java.io.Serializable

data class Room(
    var _id: String,
    var name: String,
    var hotel: String,
    var roomType: String,
    var amenitiesIds: List<String>,
    var isAccepted: Boolean,
    var isBooked: Boolean,
    var image: List<String>,
) : Serializable {
    constructor() : this("", "", "", "", listOf(), false, false, listOf())
}
