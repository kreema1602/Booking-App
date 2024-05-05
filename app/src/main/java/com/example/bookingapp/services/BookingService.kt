package com.example.bookingapp.services

import com.example.bookingapp.models.ApiResponse
import com.example.bookingapp.models.Booking
import com.example.bookingapp.view_models.MainViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object BookingService {
    private val apiService: ApiService by lazy {
        RetrofitClient.apiService
    }

    suspend fun getWaitingBooking(): List<Booking> {
        try {
            val response = apiService.getWaitingBookings(MainViewModel.authViewModel.account.role, MainViewModel.authViewModel.account._id)
            val statusCode = response.code()

            return if (statusCode == 200) {
                val apiResponse = response.body() as ApiResponse

                Gson().fromJson(
                    Gson().toJson(apiResponse.data),
                    object : TypeToken<List<Booking>>() {}.type
                )
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ApiResponse::class.java)

                throw Exception(errorResponse.message)
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }

    suspend fun getAcceptedBooking(): List<Booking> {
        try {
            val response = apiService.getAcceptedBookings(MainViewModel.authViewModel.account.role, MainViewModel.authViewModel.account._id)
            val statusCode = response.code()

            return if (statusCode == 200) {
                val apiResponse = response.body() as ApiResponse

                Gson().fromJson(
                    Gson().toJson(apiResponse.data),
                    object : TypeToken<List<Booking>>() {}.type
                )
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ApiResponse::class.java)

                throw Exception(errorResponse.message)
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }

    suspend fun getStayingBooking(): List<Booking> {
        try {
            val response = apiService.getStayingBookings(MainViewModel.authViewModel.account.role, MainViewModel.authViewModel.account._id)
            val statusCode = response.code()

            return if (statusCode == 200) {
                val apiResponse = response.body() as ApiResponse

                Gson().fromJson(
                    Gson().toJson(apiResponse.data),
                    object : TypeToken<List<Booking>>() {}.type
                )
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ApiResponse::class.java)

                throw Exception(errorResponse.message)
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }

    suspend fun acceptBooking(bookingId: String) {
        try {
            val response = apiService.acceptBooking(MainViewModel.authViewModel.account.role, bookingId)
            val statusCode = response.code()

            if (statusCode != 200) {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ApiResponse::class.java)

                throw Exception(errorResponse.message)
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }
    suspend fun rejectBooking(bookingId: String) {
        try {
            val response = apiService.rejectBooking(MainViewModel.authViewModel.account.role, bookingId)
            val statusCode = response.code()

            if (statusCode != 200) {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ApiResponse::class.java)

                throw Exception(errorResponse.message)
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }
    suspend fun checkInBooking(bookingId: String) {
        try {
            val response = apiService.checkInBooking(MainViewModel.authViewModel.account.role, bookingId)
            val statusCode = response.code()

            if (statusCode != 200) {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ApiResponse::class.java)

                throw Exception(errorResponse.message)
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }
    suspend fun checkOutBooking(bookingId: String) {
        try {
            val response = apiService.checkOutBooking(MainViewModel.authViewModel.account.role, bookingId)
            val statusCode = response.code()

            if (statusCode != 200) {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ApiResponse::class.java)

                throw Exception(errorResponse.message)
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }
}