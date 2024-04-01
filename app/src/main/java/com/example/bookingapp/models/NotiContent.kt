package com.example.bookingapp.models

class NotiContent(variant: String, hotelName: String? = null, reason: String? = null, customer: String? = null) {
    val content: String = when (variant) {
        "booking" -> "is accepted by $hotelName"
        "cancel" -> "is cancelled by $hotelName due to $reason"
        "order" -> "is placed by $customer"
        "alert" -> "is reported by not ensuring the quality"
        else -> "Invalid notification type"
    }
}