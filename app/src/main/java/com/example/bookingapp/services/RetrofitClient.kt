package com.example.bookingapp.services

import android.util.Log
import com.example.bookingapp.CurrentAccount
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor

object RetrofitClient {
    private var BASE_URL = "https://fd00-42-115-164-149.ngrok-free.app/"
    private val authInterceptor = AuthInterceptor()
    private val client = OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    private var authToken: String? = null

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    fun setAuthToken(token: String?) {
        authToken = token
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()
        retrofit.newBuilder()
            .client(httpClient)
            .build()
    }

    fun clearAuthToken() {
        authToken = null
        val httpClient = OkHttpClient.Builder().build()
        retrofit.newBuilder()
            .client(httpClient)
            .build()
    }

    fun getAuthToken(): String? {
        return authToken
    }

    class AuthInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val original = chain.request()
            val request = authToken?.let {
                original.newBuilder()
                    .header("Authorization", "Bearer $it")
                    .build()
            } ?: original

            return chain.proceed(request)
        }
    }
}

