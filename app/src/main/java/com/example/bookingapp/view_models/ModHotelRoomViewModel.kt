package com.example.bookingapp.view_models

import androidx.lifecycle.ViewModel
import com.example.bookingapp.services.HotelRoomService

class ModHotelRoomViewModel : ViewModel() {
    suspend fun addHotelRoom(fields: Map<String, String>, role: String): Boolean {
        return HotelRoomService.addHotelRoom(fields, role)
    }
}