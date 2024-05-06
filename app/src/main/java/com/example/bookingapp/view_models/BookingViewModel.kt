package com.example.bookingapp.view_models

import androidx.lifecycle.ViewModel
import com.example.bookingapp.models.Booking
import com.example.bookingapp.models.ReservationItem
import com.example.bookingapp.models.requests.BookingRequest
import com.example.bookingapp.services.BookingService

class BookingViewModel : ViewModel() {
    suspend fun booking(newBook: BookingRequest): Boolean {
        try {
            return BookingService.booking(newBook)
        } catch (e: Exception) {
            throw Exception("BookingViewModel: ${e.message}")
        }
    }

    suspend fun getBookingOfCustomer(): List<ReservationItem> {
        try {
            return BookingService.getBookingOfCustomer()
        } catch (e: Exception) {
            throw Exception("BookingViewModel: ${e.message}")
        }
    }

    suspend fun getDetailBooking(id: String): ReservationItem {
        try {
            return BookingService.getDetailBooking(id)
        } catch (e: Exception) {
            throw Exception("BookingViewModel: ${e.message}")
        }
    }
}