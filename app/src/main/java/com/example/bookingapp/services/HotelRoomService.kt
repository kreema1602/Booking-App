package com.example.bookingapp.services

import android.util.Log
import com.example.bookingapp.models.Account
import com.example.bookingapp.models.Amenity
import com.example.bookingapp.models.ApiResponse
import com.example.bookingapp.models.RoomFullDetail
import com.example.bookingapp.models.requests.CreateRoomReq
import com.example.bookingapp.view_models.MainViewModel.authViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

object HotelRoomService {
    private val apiService: ApiService by lazy {
        RetrofitClient.apiService
    }

    suspend fun getHotels(start: Int, num: Int): List<Account> {
        try {
            val response = apiService.getHotels(
                authViewModel.account.role,
                authViewModel.account._id,
                start,
                num
            )
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

    suspend fun getAverageRating(hotelId: String): Double {
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

    suspend fun getPriceRange(hotelId: String): Pair<Double, Double> {
        try {
            val response = apiService.getPriceRange(authViewModel.account.role, hotelId)
            val statusCode = response.code()

            return if (statusCode == 200) {
                val apiResponse = response.body() as ApiResponse
                val data = JSONObject(apiResponse.data.toString())

                val minPrice = data.getString("min").toDouble() / 1000.0
                val maxPrice = data.getString("max").toDouble() / 1000.0
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

    suspend fun getRoomFullDetail(hotelId: String): List<RoomFullDetail> {
        try {
            val response = apiService.getRoomFullDetail(authViewModel.account.role, hotelId)
            val statusCode = response.code()

            return if (statusCode == 200) {
                val apiResponse = response.body() as ApiResponse

                Gson().fromJson(
                    Gson().toJson(apiResponse.data),
                    object : TypeToken<List<RoomFullDetail>>() {}.type
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
                return emptyList()
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }

    suspend fun addHotelRoom(fields: Map<String, String>, role: String): Boolean {
        try {
            // Log the request body before making the API call
            Log.d("Request", "Fields: $fields, Role: $role")
            val response = apiService.addRoom(
                role,
                CreateRoomReq(
                    fields["hotelName"]!!,
                    fields["roomType"]!!,
                    fields["roomName"]!!,
                    fields["isAccepted"]!!.toBoolean(),
                    fields["isBooked"]!!.toBoolean(),
                    fields["img"]!!,
                    Gson().fromJson(
                        fields["amenity"]!!, object : TypeToken<List<String>>() {}.type
                    )
                )
            )

            val statusCode = response.code()

            return if (statusCode == 200) {
                true
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ApiResponse::class.java)
                Log.e("ErrorResponsess", errorResponse.message)
                throw Exception(errorResponse.message)
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }
}