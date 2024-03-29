package com.example.bookingapp.navigation

sealed class RootScreen(val route: String) {

    object Home : RootScreen("home_root")
    object Reservations : RootScreen("reservations_root")
    object Notifications : RootScreen("notifications_root")
    object Profile : RootScreen("profile_root")
    object Login : RootScreen("login_root")
}

sealed class LeafScreen(val route: String) {
    object Home : LeafScreen("home")
    object Reservations : LeafScreen("reservations")
    object ReservationDetail : LeafScreen("reservation_detail")
    object Notifications : LeafScreen("notifications")
    object Profile : LeafScreen("profile")
    object ProfileEditor : LeafScreen("profile_editor")
    object Details : LeafScreen("details")
    object Login : LeafScreen("login")
    object SignUp : LeafScreen("sign_up")
    object SignUpForm : LeafScreen("sign_up_form")
    object ForgotPassword : LeafScreen("forgot_password")
    object NewPassword : LeafScreen("new_password")
    object RoomScreen : LeafScreen("room_screen")
    object RoomDetail : LeafScreen("room_detail")
}