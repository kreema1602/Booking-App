package com.example.bookingapp.mock_data

import com.example.bookingapp.R
import com.example.bookingapp.models.Room

object RoomData {
    val data: List<Room> = listOf(
        Room(
            1,
            "Queen Room",
            4.5f,
            "1234 Room Street",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            listOf(
                R.drawable.hotel2,
                R.drawable.hotel3,
            ),
            listOf(
                "Facility 1",
                "Facility 2",
                "Facility 3"
            ),
            "Deluxe"
        ),
    )
}