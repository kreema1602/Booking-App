package com.example.bookingapp.navigation

sealed class RootScreen (val route: String) {
    object Login : RootScreen("login_root")
    object Customer : RootScreen("customer_root")
    object Moderator : RootScreen("moderator_root")
}
sealed class CustomerLeafScreen(val route: String){
    object Home : CustomerLeafScreen("cus_home")
    object Reservation : CustomerLeafScreen("cus_reservation")
    object ReservationDetail : CustomerLeafScreen("cus_reservation_detail")
    object Notification : CustomerLeafScreen("cus_notification")
    object Profile : CustomerLeafScreen("cus_profile")
    object ProfileEditor : CustomerLeafScreen("cus_profile_editor")
    object Room : CustomerLeafScreen("cus_room")
    object RoomDetail : CustomerLeafScreen("cus_room_detail")
}
sealed class ModeratorLeafScreen(val route: String){
    object Home : ModeratorLeafScreen("mod_home")
    object Notification : ModeratorLeafScreen("mod_notification")
    object Profile : ModeratorLeafScreen("mod_profile")
    object ProfileEditor : ModeratorLeafScreen("mod_profile_editor")
    object Room : ModeratorLeafScreen("mod_room")
    object RoomAdd : ModeratorLeafScreen("mod_room_add")
    object RoomEdit : ModeratorLeafScreen("mod_room_edit")
    object RoomRemove : ModeratorLeafScreen("mod_room_remove")

}
sealed class GeneralLeafScreen(val route: String){
    object Login : GeneralLeafScreen("login")
    object SignUp : GeneralLeafScreen("sign_up")
    object SignUpForm : GeneralLeafScreen("sign_up_form")
    object ForgotPassword : GeneralLeafScreen("forgot_password")
    object NewPassword : GeneralLeafScreen("new_password")
}