package com.example.bookingapp.view_models

import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bookingapp.models.Account
import com.example.bookingapp.models.Amenity
import com.example.bookingapp.models.RoomFullDetail
import com.example.bookingapp.services.HotelRoomService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar

class CusHotelRoomViewModel : ViewModel() {
    var selectedHotelId by mutableStateOf("")
    var selectedRoomId by mutableStateOf("")
    var room by mutableStateOf(emptyList<RoomFullDetail>())
    var hotel by mutableStateOf(Account())
    var checkIn by mutableLongStateOf(Calendar.getInstance().apply {
        add(Calendar.DATE, 1)
    }.timeInMillis)
    var checkOut by mutableLongStateOf(Calendar.getInstance().apply {
        add(Calendar.DATE, 2)
    }.timeInMillis)

    suspend fun fetchHotelData() {
        try {
            withContext(Dispatchers.IO) {
                hotel = getHotel(selectedHotelId)
                room = getRoomData(selectedHotelId)
            }
        } catch (e: Exception) {
            throw Exception("CusHotelRoomViewModel: ${e.message}")
        }
    }

    suspend fun getHotels(start: Int = 0, num: Int = 20): List<Account> {
        try {
            return withContext(Dispatchers.IO) { HotelRoomService.getHotels(start, num) }
        } catch (e: Exception) {
            throw Exception("CusHotelRoomViewModel: ${e.message}")
        }
    }

    private suspend fun getHotel(hotelId: String): Account {
        try {
            return withContext(Dispatchers.IO) { HotelRoomService.getHotel(hotelId) }
        } catch (e: Exception) {
            throw Exception("CusHotelRoomViewModel: ${e.message}")
        }
    }

    suspend fun getAverageRating(hotelId: String): Double {
        try {
            return withContext(Dispatchers.IO) { HotelRoomService.getAverageRating(hotelId) }
        } catch (e: Exception) {
            throw Exception("CusHotelRoomViewModel: ${e.message}")
        }
    }

    suspend fun getPriceRange(hotelId: String): Pair<Double, Double> {
        try {
            return withContext(Dispatchers.IO) { HotelRoomService.getPriceRange(hotelId) }
        } catch (e: Exception) {
            throw Exception("CusHotelRoomViewModel: ${e.message}")
        }
    }

    suspend fun getRoomData(hotelId: String): List<RoomFullDetail> {
        try {
            return withContext(Dispatchers.IO) { HotelRoomService.getRoomFullDetail(hotelId) }
        } catch (e: Exception) {
            throw Exception("CusHotelRoomViewModel: ${e.message}")
        }
    }

    suspend fun getHotelAmenities(hotelId: String): List<Amenity> {
        try {
            return withContext(Dispatchers.IO) { HotelRoomService.getHotelAmenities(hotelId) }
        } catch (e: Exception) {
            throw Exception("CusHotelRoomViewModel: ${e.message}")
        }
    }
}