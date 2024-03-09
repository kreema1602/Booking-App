package com.example.bookingapp

data class ReservationItem(
    val name: String,
    val status: String,
    val date: String,
    val price: String,
    val roomType: String,
    val imageResource: Int
){
    companion object {
        fun getSampleData(): List<ReservationItem> {
            return listOf(
                ReservationItem(
                    "New World Hotel",
                    "Waiting",
                    "Thu, 4/6/2024 - Sat, 6/6/2024",
                    "400.000",
                    "Queen Room (Deluxe)",
                    R.drawable.image1
                ),
                ReservationItem(
                    "Sheraton Hotel",
                    "Cancelled",
                    "Thu, 4/6/2024 - Sat, 6/6/2024",
                    "400.000",
                    "King Room (Luxury)",
                    R.drawable.image2
                ),
                ReservationItem(
                    "Majestic Hotel",
                    "Completed",
                    "Thu, 4/6/2024 - Sat, 6/6/2024",
                    "400.000",
                    "Queen Room (Deluxe)",
                    R.drawable.image1
                ),
                ReservationItem(
                    "New World Hotel",
                    "Cancelled",
                    "Thu, 4/6/2024 - Sat, 6/6/2024",
                    "400.000",
                    "King Room (Luxury)",
                    R.drawable.image2
                ),
                ReservationItem(
                    "New World Hotel",
                    "Completed",
                    "Thu, 4/6/2024 - Sat, 6/6/2024",
                    "400.000",
                    "Queen Room (Deluxe)",
                    R.drawable.image1
                ),
                ReservationItem(
                    "New World Hotel",
                    "Completed",
                    "Thu, 4/6/2024 - Sat, 6/6/2024",
                    "400.000",
                    "King Room (Luxury)",
                    R.drawable.image2
                ),
                ReservationItem(
                    "New World Hotel",
                    "Waiting",
                    "Thu, 4/6/2024 - Sat, 6/6/2024",
                    "400.000",
                    "Queen Room (Deluxe)",
                    R.drawable.image1
                ),
                ReservationItem(
                    "New World Hotel",
                    "Completed",
                    "Thu, 4/6/2024 - Sat, 6/6/2024",
                    "400.000",
                    "King Room (Luxury)",
                    R.drawable.image2
                ),
                ReservationItem(
                    "New World Hotel",
                    "Waiting",
                    "Thu, 4/6/2024 - Sat, 6/6/2024",
                    "400.000",
                    "Queen Room (Deluxe)",
                    R.drawable.image1
                ),
                ReservationItem(
                    "New World Hotel",
                    "Cancelled",
                    "Thu, 4/6/2024 - Sat, 6/6/2024",
                    "400.000",
                    "King Room (Luxury)",
                    R.drawable.image2
                ),
                ReservationItem(
                    "New World Hotel",
                    "Waiting",
                    "Thu, 4/6/2024 - Sat, 6/6/2024",
                    "400.000",
                    "Queen Room (Deluxe)",
                    R.drawable.image1
                ),
                ReservationItem(
                    "New World Hotel",
                    "Cancelled",
                    "Thu, 4/6/2024 - Sat, 6/6/2024",
                    "400.000",
                    "King Room (Luxury)",
                    R.drawable.image2
                ),
                ReservationItem(
                    "New World Hotel",
                    "Waiting",
                    "Thu, 4/6/2024 - Sat, 6/6/2024",
                    "400.000",
                    "Queen Room (Deluxe)",
                    R.drawable.image1
                ),
                ReservationItem(
                    "New World Hotel",
                    "Cancelled",
                    "Thu, 4/6/2024 - Sat, 6/6/2024",
                    "400.000",
                    "King Room (Luxury)",
                    R.drawable.image2
                ),
            )
        }
    }
}