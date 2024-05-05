package com.example.bookingapp.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookingapp.models.Account
import com.example.bookingapp.models.Amenity
import com.example.bookingapp.models.RoomFullDetail
import com.example.bookingapp.services.HotelRoomService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class CusHotelRoomViewModel : ViewModel() {

    private val _selectedHotelId = MutableStateFlow("")
    val selectedHotelId get() = _selectedHotelId.asStateFlow()

    private val _selectedRoomId = MutableStateFlow("")
    val selectedRoomId get() = _selectedRoomId.asStateFlow()


    private var _hotel = MutableStateFlow(Account())
    val hotel get() = _hotel.asStateFlow()

    private val _room = MutableStateFlow<List<RoomFullDetail>?>(null)
    val room get() = _room.asStateFlow()

    private val _checkIn = MutableStateFlow(
        Calendar.getInstance().apply {
            add(Calendar.DATE, 1)
        }.timeInMillis
    )

    val checkIn get() = _checkIn.asStateFlow()

    private val _checkOut = MutableStateFlow(
        Calendar.getInstance().apply {
            add(Calendar.DATE, 2)
        }.timeInMillis
    )

    val checkOut get() = _checkOut.asStateFlow()

    private val _hotels = MutableStateFlow<List<Account>?>(null)
    val hotels get() = _hotels.asStateFlow()

    private val _averageRating = MutableStateFlow(0.0)
    val averageRating get() = _averageRating.asStateFlow()

    private val _priceRange = MutableStateFlow(Pair(0.0, 0.0))
    val priceRange get() = _priceRange.asStateFlow()

    private val _hotelAmenities = MutableStateFlow<List<Amenity>?>(null)
    val hotelAmenities get() = _hotelAmenities.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error get() = _error.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading get() = _isLoading.asStateFlow()


    fun getHotels(start: Int = 0, num: Int = 20): List<Account> {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _hotels.value = HotelRoomService.getHotels("customer", start, num)
                Log.d("CusHotelRoomViewModel", "Hotels: $hotels")
            } catch (e: Exception) {
                _error.value = "Failed to fetch hotel data: ${e.message}"
                Log.e("CusHotelRoomViewModel", "Failed to get hotels: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
        return _hotels.value ?: emptyList()
    }

    fun getHotel(role: String, hotelId: String): Account {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _hotel.value = HotelRoomService.getHotel(role, hotelId)
            } catch (e: Exception) {
                _error.value = "Failed to fetch hotel data: ${e.message}"
                Log.e("CusHotelRoomViewModel", "Failed to get hotel: ${e.message}")
            } finally {
                _isLoading.value = false
                _hotel.value = Account()
            }
        }
        return _hotel.value
    }

    fun fetchHotelData(role: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _hotel.value = getHotel(role, selectedHotelId.value)
                _room.value = getRoomData(role, selectedHotelId.value)
            } catch (e: Exception) {
                _error.value = "Failed to fetch hotel data: ${e.message}"
                Log.e("CusHotelRoomViewModel", "Failed to fetch hotel data: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getAverageRating(role: String, hotelId: String): Double {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _averageRating.value = HotelRoomService.getAvaregeRating(role, hotelId)
            } catch (e: Exception) {
                _error.value = "Failed to fetch average rating: ${e.message}"
                _averageRating.value = 0.0
                throw Exception("CusHotelRoomViewModel: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
        return _averageRating.value
    }

    fun getPriceRange(role: String, hotelId: String): Pair<Double, Double> {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _priceRange.value = HotelRoomService.getPriceRange(role, hotelId)
            } catch (e: Exception) {
                _error.value = "Failed to fetch price range: ${e.message}"
                _priceRange.value = Pair(0.0, 0.0)
                throw Exception("CusHotelRoomViewModel: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
        return _priceRange.value
    }

    fun selectHotel(hotelId: String) {
        _selectedHotelId.value = hotelId
    }

    fun setCheckIn(date: Long) {
        _checkIn.value = date
    }

    fun setCheckOut(date: Long) {
        _checkOut.value = date
    }

    fun getRoomData(role: String, hotelId: String): List<RoomFullDetail> {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _room.value = HotelRoomService.getRoomFullDetail(role, hotelId)
            } catch (e: Exception) {
                _error.value = "Failed to fetch room data: ${e.message}"
                _room.value = emptyList()
                throw Exception("CusHotelRoomViewModel: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
        return _room.value ?: emptyList()
    }

    fun getHotelAmenities(role: String, hotelId: String): List<Amenity> {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _hotelAmenities.value = HotelRoomService.getHotelAmenities(role, hotelId)
            } catch (e: Exception) {
                _error.value = "Failed to fetch hotel amenities: ${e.message}"
                _hotelAmenities.value = emptyList()
                throw Exception("CusHotelRoomViewModel: ${e.message}")
            } finally {
                _isLoading.value = false
            }

        }
        return _hotelAmenities.value ?: emptyList()
    }
}