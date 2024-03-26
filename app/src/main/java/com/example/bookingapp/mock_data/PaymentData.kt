package com.example.bookingapp.mock_data

import com.example.bookingapp.models.Payment

object PaymentData {
    val data: List<Payment> = listOf(
        Payment(
            1,
            100.0,
            "2021-10-01",
            "2021-10-03",
            2
        )
    )
}