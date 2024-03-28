package com.example.bookingapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bookingapp.mock_data.HotelData
import com.example.bookingapp.models.JoyhubAccount
import com.example.bookingapp.pages.ForgotPasswordPage
import com.example.bookingapp.pages.HomeDetailsPage
import com.example.bookingapp.pages.HomePage
import com.example.bookingapp.pages.NewPasswordPage
import com.example.bookingapp.pages.NotificationPage
import com.example.bookingapp.pages.NotificationViewModel
import com.example.bookingapp.pages.ProfileFieldEditor
import com.example.bookingapp.pages.ProfilePage
import com.example.bookingapp.pages.ReservationDetailScreen
import com.example.bookingapp.pages.ReservationPage
import com.example.bookingapp.pages.SignUpForm
import com.example.bookingapp.pages.SignUpPage
import com.example.bookingapp.pages.RoomDetail
import com.example.bookingapp.pages.RoomScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = RootScreen.Home.route) {
        addHomeRoute(navController)
        addReservationsRoute(navController)
        addNotificationsRoute(navController)
        addProfileRoute(navController)
        addLoginRoute(navController)
    }
}

// Home navigation
private fun NavGraphBuilder.addHomeRoute(navController: NavController) {
    navigation(
        route = RootScreen.Home.route,
        startDestination = LeafScreen.Home.route
    ) {
        showHome(navController)
        showRoomScreen(navController)
        showRoomDetail(navController)
    }
}

private fun NavGraphBuilder.showHome(navController: NavController) {
    composable(LeafScreen.Home.route) {
        HomePage(
            showRoomScreen = {
                navController.navigate(LeafScreen.RoomScreen.route + "/$it")
            }
        )
    }
}

private fun NavGraphBuilder.showRoomScreen(navController: NavController) {
    composable(LeafScreen.RoomScreen.route + "/{hotelId}") { it ->
        val hotelId = it.arguments?.getString("hotelId")?.toInt() ?: 0
        RoomScreen(hotelId, onBack = {
            navController.navigateUp()
        }, showRoomDetail = {
            navController.navigate(LeafScreen.RoomDetail.route + "/$it")
        })
    }
}

private fun NavGraphBuilder.showRoomDetail(navController: NavController) {
    composable(LeafScreen.RoomDetail.route + "/{roomId}") {
        val roomId = it.arguments?.getString("roomId")?.toInt() ?: 0
        RoomDetail(roomId, onBack = {
            navController.navigateUp()
        })
    }
}

// Reservations navigation
private fun NavGraphBuilder.addReservationsRoute(navController: NavController) {
    navigation(
        route = RootScreen.Reservations.route,
        startDestination = LeafScreen.Reservations.route
    ) {
        showReservations(navController)
        showReservationDetail(navController)
    }
}

private fun NavGraphBuilder.showReservations(navController: NavController) {
    composable(LeafScreen.Reservations.route) {
        ReservationPage(navController)
    }
}

private fun NavGraphBuilder.showReservationDetail(navController: NavController) {
    composable(LeafScreen.ReservationDetail.route + "/{hotelId}") {
        val hotelId = it.arguments?.getString("hotelId")?.toInt() ?: 0
        ReservationDetailScreen(hotelId, navController = navController)
    }
}

// end of Reservations navigation

// Notifications navigation
private fun NavGraphBuilder.addNotificationsRoute(navController: NavController) {
    navigation(
        route = RootScreen.Notifications.route,
        startDestination = LeafScreen.Notifications.route
    ) {
        showNotifications(navController)
    }
}

private fun NavGraphBuilder.showNotifications(navController: NavController) {
    composable(LeafScreen.Notifications.route) {
        NotificationPage(
            viewModel = NotificationViewModel(
                savedStateHandle = SavedStateHandle(),
                notificationList = arrayListOf()
            )
        )
    }
}
// end of Notifications navigation

// Profile navigation
private fun NavGraphBuilder.addProfileRoute(navController: NavController) {
    navigation(
        route = RootScreen.Profile.route,
        startDestination = LeafScreen.Profile.route
    ) {
        showProfile(navController)
        showProfileEditor(navController)
    }
}

private fun NavGraphBuilder.showProfile(navController: NavController) {
    composable(LeafScreen.Profile.route) {
        ProfilePage(accId = 1, onClickEdit = {
            navController.navigate(LeafScreen.ProfileEditor.route + "/$it")
        })
    }
}

private fun NavGraphBuilder.showProfileEditor(navController: NavController) {
    composable(LeafScreen.ProfileEditor.route + "/{accId}") {
        val accId = it.arguments?.getString("accId")?.toInt() ?: 0
        ProfileFieldEditor(accId = accId, onBack = {
            navController.navigateUp()
        })
    }
}
// end of Profile navigation

// Login navigation
private fun NavGraphBuilder.addLoginRoute(navController: NavController) {
    navigation(
        route = RootScreen.Login.route,
        startDestination = LeafScreen.SignUp.route
    ) {
        showLogin(navController)
        showSignUp(navController)
        showSignUpForm(navController)
        showForgotPassword(navController)
        showNewPassword(navController)
    }
}

private fun NavGraphBuilder.showLogin(navController: NavController) {
    composable(LeafScreen.Login.route) {

    }
}

private fun NavGraphBuilder.showSignUp(navController: NavController) {
    composable(LeafScreen.SignUp.route) {
        SignUpPage(navController = navController)
    }
}

private fun NavGraphBuilder.showSignUpForm(navController: NavController) {
    composable(LeafScreen.SignUpForm.route + "/{role}") {
        val role = it.arguments?.getString("role") ?: ""
        SignUpForm(navController = navController, role = role)
    }
}

private fun NavGraphBuilder.showForgotPassword(navController: NavController) {
    composable(LeafScreen.ForgotPassword.route) {
        ForgotPasswordPage(navController = navController)
    }
}

private fun NavGraphBuilder.showNewPassword(navController: NavController) {
    composable(LeafScreen.NewPassword.route) {
        NewPasswordPage(navController = navController)
    }
}
// end of Login navigation