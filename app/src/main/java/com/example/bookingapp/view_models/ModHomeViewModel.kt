package com.example.bookingapp.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bookingapp.models.Booking
import com.example.bookingapp.services.BookingService

class ModHomeViewModel: ViewModel() {
    var waitingBooking by mutableStateOf(emptyList<Booking>())
    var acceptedBooking by mutableStateOf(emptyList<Booking>())
    var stayingBooking by mutableStateOf(emptyList<Booking>())
    var statusNotification by mutableStateOf("")

    suspend fun loadWaitingBooking() {
        try {
            waitingBooking = BookingService.getWaitingBooking()
        } catch (e: Exception) {
            throw Exception("Mod home view model: ${e.message}")
        }
    }

    suspend fun loadAcceptedBooking() {
        try {
            acceptedBooking = BookingService.getAcceptedBooking()
        } catch (e: Exception) {
            throw Exception("Mod home view model: ${e.message}")
        }
    }

    suspend fun loadStayingBooking() {
        try {
            stayingBooking = BookingService.getStayingBooking()
        } catch (e: Exception) {
            throw Exception("Mod home view model: ${e.message}")
        }
    }

    suspend fun acceptBooking(bookingId: String) {
        try {
            BookingService.acceptBooking(bookingId)
            statusNotification = "Booking accepted"
        } catch (e: Exception) {
            throw Exception("Mod home view model: ${e.message}")
        }
    }

    suspend fun rejectBooking(bookingId: String) {
        try {
            BookingService.rejectBooking(bookingId)
            statusNotification = "Booking rejected"
        } catch (e: Exception) {
            throw Exception("Mod home view model: ${e.message}")
        }
    }

    suspend fun checkInBooking(bookingId: String) {
        try {
            BookingService.checkInBooking(bookingId)
            statusNotification = "Booking checked in"
        } catch (e: Exception) {
            throw Exception("Mod home view model: ${e.message}")
        }
    }

    suspend fun checkOutBooking(bookingId: String) {
        try {
            BookingService.checkOutBooking(bookingId)
            statusNotification = "Booking checked out"
        } catch (e: Exception) {
            throw Exception("Mod home view model: ${e.message}")
        }
    }
}