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
import com.example.bookingapp.pages.HomeDetailsPage
import com.example.bookingapp.pages.HomePage
import com.example.bookingapp.pages.NotificationPage
import com.example.bookingapp.pages.NotificationViewModel
import com.example.bookingapp.pages.ProfileFieldEditor
import com.example.bookingapp.pages.ProfilePage
import com.example.bookingapp.pages.ReservationDetailScreen
import com.example.bookingapp.pages.ReservationPage
import com.example.bookingapp.pages.RoomScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = RootScreen.Home.route) {
        addHomeRoute(navController)
        addReservationsRoute(navController)
        addNotificationsRoute(navController)
        addProfileRoute(navController)
    }
}

// Home navigation
private fun NavGraphBuilder.addHomeRoute(navController: NavController) {
    navigation(
        route = RootScreen.Home.route,
        startDestination = LeafScreen.Home.route
    ) {
        showHome(navController)
        showDetails(navController)
    }
}

private fun NavGraphBuilder.showHome(navController: NavController) {
    composable(LeafScreen.Home.route) {
        HomePage(
            showDetail = {
                navController.navigate(LeafScreen.Details.route)
            }
        )
    }
}

private fun NavGraphBuilder.showDetails(navController: NavController) {
    composable(LeafScreen.Details.route) {
        RoomScreen(hotel = HotelData.data[0], onBack = {
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