package com.example.bookingapp.services

import android.util.Log
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

    suspend fun login(username: String, password: String): Pair<Account, String>? {
        try {
            val request = LoginRequest(username, password)
            val response = apiService.login(request)
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
            var response: Response<ApiResponse>? = null
            var statusCode: Int? = null
            if (role == "customer") {
                val request = CusRegisterRequest(
                    fields["username"]!!,
                    fields["password"]!!,
                    fields["fullName"]!!,
                    role
                )

                response = apiService.register_customer(request)

                statusCode = response.code()
            } else if (role == "moderator") {
                val request = ModRegisterRequest(
                    fields["username"]!!,
                    fields["password"]!!,
                    fields["hotelName"]!!,
                    fields["hotelAddress"]!!,
                    fields["description"]!!,
                    role
                )
                response = apiService.register_moderator(request)

                statusCode = response.code()

            } else {
                throw Exception("Invalid role")
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