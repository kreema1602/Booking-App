package com.example.bookingapp.navigation

sealed class RootScreen(val route: String) {

    object Home : RootScreen("home_root")
    object Reservations : RootScreen("reservations_root")
    object Notifications : RootScreen("notifications_root")
    object Profile : RootScreen("profile_root")
}

sealed class LeafScreen(val route: String) {
    object Home : LeafScreen("home")
    object Reservations : LeafScreen("reservations")
    object Notifications : LeafScreen("notifications")
    object Profile : LeafScreen("profile")
    object Details : LeafScreen("details")
}