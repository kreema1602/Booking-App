package com.example.bookingapp.models

import java.io.Serializable

data class JoyhubAccount(
    var username: String,
    var password: String,
    var email: String,
    var phone: String,
    var wallet: Int
) : Serializable