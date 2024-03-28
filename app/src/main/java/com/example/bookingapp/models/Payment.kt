package com.example.bookingapp.models

import java.io.Serializable

data class Payment(
    var id: Int,
    var perNight: Double,
    var from: String,
    var to: String,
    var nights: Int,
) : Serializable