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
    var hotel_name: String = "",
    var hotel_address: String = "",
    var description: String = "",
    var image: String = "",
) : Serializable {
    constructor() : this("", "", "", "", "")
}