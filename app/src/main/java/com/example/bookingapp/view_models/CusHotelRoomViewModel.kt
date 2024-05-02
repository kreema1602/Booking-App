package com.example.bookingapp.view_models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bookingapp.models.Account
import com.example.bookingapp.models.Amenity
import com.example.bookingapp.models.Room
import com.example.bookingapp.services.HotelRoomService

class CusHotelRoomViewModel: ViewModel() {
    var selectedHotelId by mutableStateOf("")

    suspend fun getHotels(): List<Account> {
        try {
            return HotelRoomService.getHotels()
        } catch (e: Exception) {
            throw Exception("CusHotelRoomViewModel: ${e.message}")
        }
    }

    suspend fun getHotel(hotelId: String): Account {
        try {
            return HotelRoomService.getHotel(hotelId)
        } catch (e: Exception) {
            throw Exception("CusHotelRoomViewModel: ${e.message}")
        }
    }

    suspend fun getAverageRating(hotelId: String): Double {
        try {
            Log.d("CusHotelRoomViewModel", "getAverageRating: $hotelId")
            return HotelRoomService.getAvaregeRating(hotelId)
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

    suspend fun getRoomData(hotelId: String): List<Room> {
        try {
            return HotelRoomService.getRoomData(hotelId)
        } catch (e: Exception) {
            throw Exception("CusHotelRoomViewModel: ${e.message}")
        }
    }

    suspend fun getHotelAmenities(hotelId: String): List<Amenity> {
        try {
            return HotelRoomService.getHotelAmenities(hotelId)
        } catch (e: Exception) {
            throw Exception("CusHotelRoomViewModel: ${e.message}")
        }
    }
}