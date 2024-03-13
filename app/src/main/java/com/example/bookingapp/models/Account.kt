package com.example.bookingapp.models

class JoyhubAccount(
    private var username: String,
    private var password: String,
    private var email: String,
    private var phone: String,
    private var wallet: Int
) {

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