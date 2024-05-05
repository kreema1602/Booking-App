package com.example.bookingapp.models

data class Notification (
    var from_id: String,
    var to_id: String,
    var forRole: String,
    var title: String,
    var content: String,
    var status: String,
    var booking: String,
    var room: String,
    var is_read: Boolean,
    var createdAt: String,
    var updatedAt: String,
) {
    constructor() : this("", "", "", "", "", "", "", "", false, "", "")
}