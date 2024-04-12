package com.example.bookingapp

import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.bookingapp.services.AccountService
import com.example.bookingapp.services.AmenityService
import kotlinx.coroutines.launch

class TestActivity: ComponentActivity() {

    // This is a test function to check if the login function works
    private suspend fun testLogin() {
        AccountService.login("abcdefh", "12345")
        AmenityService.getAmenities()
    }

    // Call the testLogin function
    init {
        lifecycleScope.launch {
            testLogin()
        }
    }
}