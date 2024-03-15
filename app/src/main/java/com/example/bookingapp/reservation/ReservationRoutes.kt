package com.example.bookingapp.reservation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ReservationNavigator() {
    navController = rememberNavController()

    NavHost(navController = navController, startDestination = "reservation_list") {
        composable("reservation_list") {
            ReservationMainScreen()
        }
        composable("reservation_detail/{hotel_id}") { backStackEntry ->
            val hotelId = backStackEntry.arguments?.getString("hotel_id")?.toInt()
            ReservationDetailScreen(hotelId!!)
        }
    }
}

@Preview
@Composable
fun ReservationNavigatorPreview() {
    ReservationNavigator()
}