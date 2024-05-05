package com.example.bookingapp.models.requests

class BookingRequest {
    var hotel: String? = null
    var room: String? = null
    var customer: String? = null
    var check_in: Long? = null
    var check_out: Long? = null
    var is_paid: Boolean? = null
    var status: String? = null
}