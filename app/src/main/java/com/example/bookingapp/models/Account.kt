package com.example.bookingapp.models

import java.io.Serializable

data class Account(
    var _id: String,
    var username: String,
    var password: String,
    var email: String,
    var role: String,
    var bankNumber: String = "",
    var wallet: Int = 0,
    var phone: String = "",
    var fullname: String = "",
    var hotelName: String = "",
    var hotelAddress: String = "",
    var description: String = "",
    var image: String = "",
    var createdAt: String = "",
    var updatedAt: String = "",
) : Serializable {
    constructor() : this("", "", "", "", "", "", 0, "", "", "", "", "", "", "", "")
}