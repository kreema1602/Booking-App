package com.example.bookingapp.mock_data

import com.example.bookingapp.R
import com.example.bookingapp.models.ReservationItem

object ReservationData {
    val sampleData: List<ReservationItem> = listOf(
        ReservationItem(
            id = 1,
            name = "New World Hotel",
            status = "Completed",
            date = "Sat 01/01/2022 - Sun 02/01/2022",
            price = "1,000,000",
            roomType = "Deluxe Room",
            imageResource = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)
        ),
        ReservationItem(
            id = 2,
            name = "Sheraton Hotel",
            status = "Waiting",
            date = "Mon 03/01/2022 - Tue 04/01/2022",
            price = "2,000,000",
            roomType = "Superior Room",
            imageResource = listOf(R.drawable.image4, R.drawable.image1, R.drawable.image2)
        ),
        ReservationItem(
            id = 3,
            name = "Majestic Hotel",
            status = "Cancelled",
            date = "Mon 03/01/2022 - Tue 04/01/2022",
            price = "3,000,000",
            roomType = "Suite Room",
            imageResource = listOf(R.drawable.image3, R.drawable.image4, R.drawable.image1)
        ),
        ReservationItem(
            id = 4,
            name = "Grand Hotel",
            status = "Completed",
            date = "Sat 01/01/2022 - Sun 02/01/2022",
            price = "4,000,000",
            roomType = "Executive Room",
            imageResource = listOf(R.drawable.image2, R.drawable.image3, R.drawable.image4)
        ),
        ReservationItem(
            id = 5,
            name = "Sunset Resort",
            status = "Completed",
            date = "Mon 03/01/2022 - Tue 04/01/2022",
            price = "5,000,000",
            roomType = "Ocean View Suite",
            imageResource = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)
        ),
        ReservationItem(
            id = 6,
            name = "Golden Sands",
            status = "Cancelled",
            date = "Sat 01/01/2022 - Sun 02/01/2022",
            price = "6,000,000",
            roomType = "Luxury Villa",
            imageResource = listOf(R.drawable.image4, R.drawable.image1, R.drawable.image2)
        ),
        ReservationItem(
            id = 7,
            name = "Crystal Palace",
            status = "Completed",
            date = "Mon 03/01/2022 - Tue 04/01/2022",
            price = "7,000,000",
            roomType = "Penthouse Suite",
            imageResource = listOf(R.drawable.image3, R.drawable.image4, R.drawable.image1)
        ),
        ReservationItem(
            id = 8,
            name = "Emerald Bay Resort",
            status = "Waiting",
            date = "Sat 01/01/2022 - Sun 02/01/2022",
            price = "8,000,000",
            roomType = "Private Bungalow",
            imageResource = listOf(R.drawable.image2, R.drawable.image3, R.drawable.image4)
        ),
        ReservationItem(
            id = 9,
            name = "Silver Heights Hotel",
            status = "Cancelled",
            date = "Mon 03/01/2022 - Tue 04/01/2022",
            price = "9,000,000",
            roomType = "Mountain View Suite",
            imageResource = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)
        ),
        ReservationItem(
            id = 10,
            name = "Royal Palace",
            status = "Completed",
            date = "Sat 01/01/2022 - Sun 02/01/2022",
            price = "10,000,000",
            roomType = "Royal Suite",
            imageResource = listOf(R.drawable.image4, R.drawable.image1, R.drawable.image2)
        ),
        ReservationItem(
            id = 11,
            name = "Lakeside Resort",
            status = "Completed",
            date = "Mon 03/01/2022 - Tue 04/01/2022",
            price = "11,000,000",
            roomType = "Lake View Villa",
            imageResource = listOf(R.drawable.image3, R.drawable.image4, R.drawable.image1)
        ),
        ReservationItem(
            id = 12,
            name = "Mountain Vista",
            status = "Cancelled",
            date = "Sat 01/01/2022 - Sun 02/01/2022",
            price = "12,000,000",
            roomType = "Scenic Chalet",
            imageResource = listOf(R.drawable.image2, R.drawable.image3, R.drawable.image4)
        ),
        ReservationItem(
            id = 13,
            name = "Ocean Breeze",
            status = "Completed",
            date = "Mon 03/01/2022 - Tue 04/01/2022",
            price = "13,000,000",
            roomType = "Beachfront Cottage",
            imageResource = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)
        ),
        ReservationItem(
            id = 14,
            name = "Palm Springs Hotel",
            status = "Waiting",
            date = "Mon 03/01/2022 - Tue 04/01/2022",
            price = "14,000,000",
            roomType = "Desert Oasis Suite",
            imageResource = listOf(R.drawable.image4, R.drawable.image1, R.drawable.image2)
        ),
        ReservationItem(
            id = 15,
            name = "Tropical Paradise",
            status = "Cancelled",
            date = "Sat 01/01/2022 - Sun 02/01/2022",
            price = "15,000,000",
            roomType = "Tropical Villa",
            imageResource = listOf(R.drawable.image3, R.drawable.image4, R.drawable.image1)
        ),
    )
}