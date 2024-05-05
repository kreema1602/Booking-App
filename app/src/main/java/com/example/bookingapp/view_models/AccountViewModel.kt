package com.example.bookingapp.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bookingapp.models.Account
import com.example.bookingapp.models.requests.UpdateAccountRequest
import com.example.bookingapp.services.AccountService

class AccountViewModel : ViewModel() {
    suspend fun getProfile(role: String, id: String): Account {
        try {
            return AccountService.getProfile(role, id)
        } catch (e: Exception) {
            throw Exception("AccountViewModel: ${e.message}")
        }
    }
    suspend fun updateProfile(role: String, id: String, newAccount: UpdateAccountRequest): Boolean {
        try {
            return AccountService.updateProfile(role, id, newAccount)
        } catch (e: Exception) {
            throw Exception("AccountViewModel: ${e.message}")
        }
    }
}