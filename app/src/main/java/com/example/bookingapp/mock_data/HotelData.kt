package com.example.bookingapp.mock_data

import com.example.bookingapp.models.Hotel

object HotelData {
    val data: List<Hotel> = listOf(
        Hotel(
            1,
            "Hotel ABC",
            4.5f,
            "123 Main St, San Francisco, CA",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            "https://developer.android.com/images/brand/Android_Robot.png",
            listOf("Free Wi-Fi", "Parking", "Swimming", "Gym")
        )
    )
}