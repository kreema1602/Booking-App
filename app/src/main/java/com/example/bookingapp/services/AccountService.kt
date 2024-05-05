package com.example.bookingapp.services

import com.example.bookingapp.models.Account
import com.example.bookingapp.models.ApiResponse
import com.example.bookingapp.models.requests.CusRegisterRequest
import com.example.bookingapp.models.requests.LoginRequest
import com.example.bookingapp.models.requests.ModRegisterRequest
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Response

object AccountService {
    private val apiService: ApiService by lazy {
        RetrofitClient.apiService
    }

    suspend fun login(username: String, password: String, isBio: Boolean): Pair<Account, String>? {
        try {
            val request = LoginRequest(username, password)
            val response = apiService.login(request, isBio)
            val statusCode = response.code()

            return if (statusCode == 200) {
                val apiResponse = response.body() as ApiResponse
                val jsonData = JSONObject(Gson().toJson(apiResponse.data))

                val account = Gson().fromJson(
                    jsonData.getJSONObject("account").toString(),
                    Account::class.java
                )
                val token = jsonData.getString("token")

                // return both account and token
                Pair(account, token)
            } else {
                RetrofitClient.clearAuthToken()

                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ApiResponse::class.java) // Parse using Gson

                throw Exception(errorResponse.message)
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }

    suspend fun register(fields: Map<String, String>, role: String): Boolean {
        try {
            val response: Response<ApiResponse>?
            val statusCode: Int?
            when (role) {
                "customer" -> {
                    val request = CusRegisterRequest(
                        fields["username"]!!,
                        fields["email"]!!,
                        fields["password"]!!,
                        fields["phone"]!!,
                        fields["fullName"]!!,
                        role
                    )

                    response = apiService.registerCustomer(request)

                    statusCode = response.code()
                }
                "moderator" -> {
                    val request = ModRegisterRequest(
                        fields["username"]!!,
                        fields["email"]!!,
                        fields["password"]!!,
                        fields["phone"]!!,
                        fields["fullName"]!!,
                        fields["hotelName"]!!,
                        fields["hotelAddress"]!!,
                        fields["description"]!!,
                        role
                    )
                    response = apiService.registerModerator(request)

                    statusCode = response.code()

                }
                else -> {
                    throw Exception("Invalid role")
                }
            }

            if (statusCode == 200) {
                return true
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ApiResponse::class.java) // Parse using Gson

                throw Exception(errorResponse.message)
            }

        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}