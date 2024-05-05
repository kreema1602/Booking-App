package com.example.bookingapp.view_models

import androidx.lifecycle.ViewModel
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
}