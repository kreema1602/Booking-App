package com.example.bookingapp.models

import java.io.Serializable

data class Hotel(
    var id: Int,
    var name: String,
    var rating: Float,
    var address: String,
    var desc: String,
    var imageUrl: String,
    var facilities: List<String>,
) : Serializable