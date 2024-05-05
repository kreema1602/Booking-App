package com.example.bookingapp.view_models

import androidx.lifecycle.ViewModel
import com.example.bookingapp.models.Notification
import com.example.bookingapp.services.NotiService

class NotiViewModel: ViewModel() {
    suspend fun getNoti(): List<Notification> {
        try {
            return NotiService.getNoti()
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }
}