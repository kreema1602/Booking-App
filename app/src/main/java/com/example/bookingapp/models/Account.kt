package com.example.bookingapp.models

import java.io.Serializable

class JoyhubAccount(s: String, s1: String, s2: String, s3: String, i: Int) : Serializable {
    private var username: String = s
    private var password: String = s1
    private var email: String = s2
    private var phone: String = s3
    private var wallet: Int = i

    fun get_username(): String {
        return username
    }

    fun get_password(): String {
        return password
    }

    fun get_email(): String {
        return email
    }

    fun get_phone(): String {
        return phone
    }

    fun get_wallet(): Int {
        return wallet
    }

    fun set_username(username: String) {
        this.username = username
    }

    fun set_password(password: String) {
        this.password = password
    }

    fun set_email(email: String) {
        this.email = email
    }

    fun set_phone(phone: String) {
        this.phone = phone
    }

    fun set_wallet(wallet: Int) {
        this.wallet = wallet
    }
}