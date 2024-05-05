package com.example.bookingapp.services

import android.content.Context
import android.util.Log
import com.example.bookingapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private var BASE_URL = BuildConfig.BASE_URL
    private lateinit var client: OkHttpClient
    private lateinit var retrofit: Retrofit
    private var authToken: String? = null
    private var initialized = false

    fun init(context: Context) {
        if (!initialized) {
            Log.d("Retrofit", "Initializing")
            val cacheSize = (10 * 1024 * 1024).toLong()
            val cache = okhttp3.Cache(context.cacheDir, cacheSize)
            client = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor())
                .cache(cache)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            initialized = true
        }
    }

    val apiService: ApiService by lazy {
        check(!initialized) {
            throw IllegalStateException("RetrofitClient not initialized")
        }
        Log.d("Retrofit", "Init completed")
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

