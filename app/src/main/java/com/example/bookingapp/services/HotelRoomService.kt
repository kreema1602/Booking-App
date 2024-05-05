package com.example.bookingapp.services

import android.util.Log
import com.example.bookingapp.models.Account
import com.example.bookingapp.models.Amenity
import com.example.bookingapp.models.ApiResponse
import com.example.bookingapp.models.RoomFullDetail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import retrofit2.Response

object HotelRoomService {
    private val apiService: ApiService by lazy {
        RetrofitClient.apiService
    }

    // generate gson with lazy initialization to avoid memory leak
    private val gson: Gson by lazy {
        Gson()
    }

    suspend fun getHotels(role: String, start: Int, num: Int): List<Account> {
        try {
            val response = apiService.getHotels(role, start, num)
            val statusCode = response.code()

            return if (statusCode == 200) {
                val apiResponse = response.body() as ApiResponse

                Gson().fromJson(
                    Gson().toJson(apiResponse.data),
                    object : TypeToken<List<Account>>() {}.type
                )
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ApiResponse::class.java)
                RetrofitClient.clearAuthToken()
                throw Exception(errorResponse.message)
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }

    suspend fun getAvaregeRating(role: String, hotelId: String): Double {
        try {
            val response = apiService.getAverageRating(role, hotelId)
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

    suspend fun getPriceRange(role: String, hotelId: String) : Pair<Double, Double> {
        try {
            val response = apiService.getPriceRange(role, hotelId)
            val statusCode = response.code()
            return if (statusCode == 200) {
                val apiResponse = response.body() as ApiResponse
                val jsonData = JSONObject(apiResponse.data.toString())
                Log.d("HotelRoomService", "getPriceRange: $jsonData")
                val minPrice = jsonData.getString("min").toDouble() / 1000.0
                val maxPrice = jsonData.getString("max").toDouble() / 1000.0

                Pair(minPrice, maxPrice)
            } else {
                val errorBody = response.errorBody()?.string() ?: throw Exception("No error body")
                val errorResponse = gson.fromJson(errorBody, ApiResponse::class.java)

                throw Exception(errorResponse.message)
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }

    private inline fun <reified T> processResponse(response: Response<ApiResponse>, optionFun: () -> Unit = {}): T {
        val statusCode = response.code()

        return if (statusCode == 200) {
            val apiResponse = response.body() ?: throw Exception("No response body")

            gson.fromJson(
                gson.toJson(apiResponse.data),
                object : TypeToken<T>() {}.type
            )
        } else {
            optionFun()
            val errorBody = response.errorBody()?.string() ?: throw Exception("No error body")
            val errorResponse = gson.fromJson(errorBody, ApiResponse::class.java)

            throw Exception(errorResponse.message)
        }
    }

    suspend fun getRoomFullDetail(role: String, hotelId: String): List<RoomFullDetail> {
        try {
            val response = apiService.getRoomFullDetail(role, hotelId)
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

    suspend fun getHotel(role: String, hotelId: String): Account {
        try {
            val response = apiService.getHotel(role, hotelId)
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

    suspend fun getHotelAmenities(role: String, hotelId: String): List<Amenity> {
        try {
            val response = apiService.getHotelAmenities(role, hotelId)
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