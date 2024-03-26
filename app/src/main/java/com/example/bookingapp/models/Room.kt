package com.example.bookingapp.models

import java.io.Serializable

data class Room(
    var id: Int,
    var name: String,
    var rating: Float,
    var address: String,
    var desc: String,
    var images: List<Int>,
    var facilities: List<Pair<String, String>>,
    var type: String,
) : Serializable
