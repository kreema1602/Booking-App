package com.example.bookingapp.services

import com.example.bookingapp.models.Account
import com.example.bookingapp.models.Amenity
import com.example.bookingapp.models.ApiResponse
import com.example.bookingapp.models.Room
import com.example.bookingapp.view_models.MainViewModel.authViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

object HotelRoomService {
    private val apiService: ApiService by lazy {
        RetrofitClient.apiService
    }

    suspend fun getHotels(): List<Account> {
        try {
            val response = apiService.getHotels(authViewModel.account.role)
            val statusCode = response.code()

            return if (statusCode == 200) {
                val apiResponse = response.body() as ApiResponse

                Gson().fromJson(
                    Gson().toJson(apiResponse.data),
                    object : TypeToken<List<Account>>() {}.type
                )
            } else {
                RetrofitClient.clearAuthToken()

                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ApiResponse::class.java)

                throw Exception(errorResponse.message)
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }

    suspend fun getAvaregeRating(hotelId: String): Double {
        try {
            val response = apiService.getAverageRating(authViewModel.account.role, hotelId)
            val statusCode = response.code()

            return if (statusCode == 200) {
                val apiResponse = response.body() as ApiResponse

                Gson().fromJson(
                    Gson().toJson(apiResponse.data),
                    Double::class.java
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

    suspend fun getPriceRange(hotelId: String) : Pair<Double, Double> {
        try {
            val response = apiService.getPriceRange(authViewModel.account.role, hotelId)
            val statusCode = response.code()

            return if (statusCode == 200) {
                val apiResponse = response.body() as ApiResponse
                val data = JSONObject(apiResponse.data.toString())

                val minPrice = data.getString("min").toDouble()
                val maxPrice = data.getString("max").toDouble()
                Pair(minPrice, maxPrice)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ApiResponse::class.java)

                throw Exception(errorResponse.message)
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }

    suspend fun getRoomData(hotelId: String): List<Room> {
        try {
            val response = apiService.getRoomData(authViewModel.account.role, hotelId)
            val statusCode = response.code()

            return if (statusCode == 200) {
                val apiResponse = response.body() as ApiResponse

                Gson().fromJson(
                    Gson().toJson(apiResponse.data),
                    object : TypeToken<List<Room>>() {}.type
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

    suspend fun getHotel(hotelId: String): Account {
        try {
            val response = apiService.getHotel(authViewModel.account.role, hotelId)
            val statusCode = response.code()

            return if (statusCode == 200) {
                val apiResponse = response.body() as ApiResponse

                Gson().fromJson(
                    Gson().toJson(apiResponse.data),
                    object : TypeToken<Account>() {}.type
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

    suspend fun getHotelAmenities(hotelId: String): List<Amenity> {
        try {
            val response = apiService.getHotelAmenities(authViewModel.account.role, hotelId)
            val statusCode = response.code()

            return if (statusCode == 200) {
                val apiResponse = response.body() as ApiResponse

                Gson().fromJson(
                    Gson().toJson(apiResponse.data),
                    object : TypeToken<List<Amenity>>() {}.type
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
}