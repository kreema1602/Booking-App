package com.example.bookingapp.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bookingapp.models.Account
import com.example.bookingapp.services.HotelRoomService

class CusHotelRoomViewModel: ViewModel() {
    var selectedHotelId by mutableIntStateOf(0)
        private set

    suspend fun getHotels(): List<Account> {
        try {
            return HotelRoomService.getHotels()
        } catch (e: Exception) {
            throw Exception("CusHotelRoomViewModel: ${e.message}")
        }
    }

    suspend fun getAverageRating(hotelId: String): Double {
        try {
            return HotelRoomService.getAverageRating(hotelId)
        } catch (e: Exception) {
            throw Exception("CusHotelRoomViewModel: ${e.message}")
        }
    }

    suspend fun getPriceRange(hotelId: String): Pair<Double, Double> {
        try {
            return HotelRoomService.getPriceRange(hotelId)
        } catch (e: Exception) {
            throw Exception("CusHotelRoomViewModel: ${e.message}")
        }
    }
}