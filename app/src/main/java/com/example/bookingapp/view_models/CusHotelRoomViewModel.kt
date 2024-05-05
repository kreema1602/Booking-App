package com.example.bookingapp.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookingapp.models.Account
import com.example.bookingapp.services.HotelRoomService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CusHotelRoomViewModel() : ViewModel() {

    private val _selectedHotelId = MutableStateFlow(0)
    val selectedHotelId get() = _selectedHotelId.asStateFlow()

    private val _hotels = MutableStateFlow<List<Account>?>(null)
    val hotels get() = _hotels.asStateFlow()

    private val _averageRating = MutableStateFlow(0.0)
    val averageRating get() = _averageRating.asStateFlow()

    private val _priceRange = MutableStateFlow(Pair(0.0, 0.0))
    val priceRange get() = _priceRange.asStateFlow()


    fun getHotels(): List<Account> {
        viewModelScope.launch {
            try {
                _hotels.value = HotelRoomService.getHotels("customer")
                Log.d("CusHotelRoomViewModel", "Hotels: $hotels")
            } catch (e: Exception) {
                Log.e("CusHotelRoomViewModel", "Failed to get hotels: ${e.message}")
            }
        }
        return _hotels.value ?: emptyList()
    }

    fun getAverageRating(hotelId: String): Double {
        viewModelScope.launch {

            try {
                _averageRating.value = HotelRoomService.getAvaregeRating("customer", hotelId)
            } catch (e: Exception) {
                throw Exception("CusHotelRoomViewModel: ${e.message}")
            }
        }
        return _averageRating.value
    }

    fun getPriceRange(hotelId: String): Pair<Double, Double> {
        viewModelScope.launch {
            try {
                _priceRange.value = HotelRoomService.getPriceRange("customer", hotelId)
            } catch (e: Exception) {
                throw Exception("CusHotelRoomViewModel: ${e.message}")
            }
        }
        return _priceRange.value
    }

    fun selectHotel(hotelId: Int) {
        _selectedHotelId.value = hotelId
    }
}