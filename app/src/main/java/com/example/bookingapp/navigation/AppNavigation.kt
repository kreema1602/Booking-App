package com.example.bookingapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bookingapp.pages.ForgotPasswordPage
import com.example.bookingapp.pages.LoginPage
import com.example.bookingapp.pages.NewPasswordPage
import com.example.bookingapp.pages.SignUpForm
import com.example.bookingapp.pages.SignUpPage
import com.example.bookingapp.pages.customer.CusFavoritePage
import com.example.bookingapp.pages.customer.CusHistoryPage
import com.example.bookingapp.pages.customer.CusHomePage
import com.example.bookingapp.pages.customer.CusNotificationPage
import com.example.bookingapp.pages.customer.CusPaymentScreen
import com.example.bookingapp.pages.customer.CusProfileFieldEditor
import com.example.bookingapp.pages.customer.CusProfilePage
import com.example.bookingapp.pages.customer.CusReservationDetail
import com.example.bookingapp.pages.customer.CusReservationPage
import com.example.bookingapp.pages.customer.CusRoomDetail
import com.example.bookingapp.pages.customer.CusRoomScreen
import com.example.bookingapp.pages.hotelier.ModHomePage
import com.example.bookingapp.pages.hotelier.ModNotificationPage
import com.example.bookingapp.pages.hotelier.ModProfilePage
import com.example.bookingapp.pages.hotelier.ModRoomAdd
import com.example.bookingapp.pages.hotelier.ModRoomPage
import com.example.bookingapp.view_models.MainViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    val authViewModel = MainViewModel.authViewModel
    val startDes = when (authViewModel.account.role) {
        "customer" -> RootScreen.Customer.route
        "moderator" -> RootScreen.Moderator.route
        else -> RootScreen.Login.route
    }
    NavHost(navController = navController, startDestination = startDes) {
        addLoginRoute(navController)
        addCustomerRoute(navController)
        addModeratorRoute(navController)
    }
}

// -------------- Login navigation ------------------- //
private fun NavGraphBuilder.addLoginRoute(
    navController: NavController
) {
    navigation(
        route = RootScreen.Login.route,
        startDestination = GeneralLeafScreen.Login.route
    ) {
        showLogin(navController)
        showSignUp(navController)
        showSignUpForm(navController)
        showForgotPassword(navController)
        showNewPassword(navController)
    }
}

private fun NavGraphBuilder.showLogin(
    navController: NavController
) {
    composable(GeneralLeafScreen.Login.route) {
        LoginPage(navController)
    }
}

private fun NavGraphBuilder.showSignUp(navController: NavController) {
    composable(GeneralLeafScreen.SignUp.route) {
        SignUpPage(navController = navController)
    }
}

private fun NavGraphBuilder.showSignUpForm(navController: NavController) {
    composable(GeneralLeafScreen.SignUpForm.route + "/{role}") {
        val role = it.arguments?.getString("role") ?: ""
        SignUpForm(navController = navController, role = role)
    }
}

private fun NavGraphBuilder.showForgotPassword(navController: NavController) {
    composable(GeneralLeafScreen.ForgotPassword.route) {
        ForgotPasswordPage(navController = navController)
    }
}

private fun NavGraphBuilder.showNewPassword(navController: NavController) {
    composable(GeneralLeafScreen.NewPassword.route) {
        NewPasswordPage(navController = navController)
    }
}
// -------------- End of Login navigation ------------------- //

// -------------- Customer navigation ------------------- //
private fun NavGraphBuilder.addCustomerRoute(navController: NavController) {
    navigation(
        route = RootScreen.Customer.route,
        startDestination = CustomerLeafScreen.Home.route
    ) {
        showCusHome(navController)
        showCusRoomScreen(navController)
        showCusRoomDetail(navController)
        showCusReservations(navController)
        showCusReservationDetail(navController)
        showCusNotifications(navController)
        showCusProfile(navController)
        showCusProfileEditor(navController)
        showCusFavorite(navController)
        showCusHistory(navController)
        showCusPayment(navController)
    }
}

private fun NavGraphBuilder.showCusHome(navController: NavController) {
    composable(CustomerLeafScreen.Home.route) {
        CusHomePage(
            showRoomScreen = {
                navController.navigate(CustomerLeafScreen.Room.route + "/$it")
            }
        )
    }
}

private fun NavGraphBuilder.showCusRoomScreen(navController: NavController) {
    composable(CustomerLeafScreen.Room.route + "/{hotelId}") { it ->
        val hotelId = it.arguments?.getString("hotelId")?.toInt() ?: 0
        CusRoomScreen(hotelId, onBack = {
            navController.navigateUp()
        }, showRoomDetail = {
            navController.navigate(CustomerLeafScreen.RoomDetail.route + "/$it")
        })
    }
}

private fun NavGraphBuilder.showCusRoomDetail(navController: NavController) {
    composable(CustomerLeafScreen.RoomDetail.route + "/{roomId}") {
        val roomId = it.arguments?.getString("roomId")?.toInt() ?: 0
        CusRoomDetail(navController, roomId, onBack = {
            navController.navigateUp()
        })
    }
}

private fun NavGraphBuilder.showCusReservations(navController: NavController) {
    composable(CustomerLeafScreen.Reservation.route) {
        CusReservationPage(navController)
    }
}

private fun NavGraphBuilder.showCusReservationDetail(navController: NavController) {
    composable(CustomerLeafScreen.ReservationDetail.route + "/{hotelId}") {
        val hotelId = it.arguments?.getString("hotelId")?.toInt() ?: 0
        CusReservationDetail(hotelId, navController = navController)
    }
}

private fun NavGraphBuilder.showCusNotifications(navController: NavController) {
    composable(CustomerLeafScreen.Notification.route) {
        CusNotificationPage(navController)
    }
}

private fun NavGraphBuilder.showCusProfile(navController: NavController) {
    composable(CustomerLeafScreen.Profile.route) {
        CusProfilePage(accId = 1, onClickEdit = {
            navController.navigate(CustomerLeafScreen.ProfileEditor.route + "/$it")
        }, onClickLogout = {
            navController.navigate(RootScreen.Login.route)
        }, onClickFavorite = {
            navController.navigate(CustomerLeafScreen.Favorite.route)
        }, onClickHistory = {
            navController.navigate(CustomerLeafScreen.History.route)
        })
    }
}

private fun NavGraphBuilder.showCusProfileEditor(navController: NavController) {
    composable(CustomerLeafScreen.ProfileEditor.route + "/{accId}") {
        val accId = it.arguments?.getString("accId")?.toInt() ?: 0
        CusProfileFieldEditor(accId = accId, onBack = {
            navController.navigateUp()
        })
    }
}

private fun NavGraphBuilder.showCusFavorite(navController: NavController) {
    composable(CustomerLeafScreen.Favorite.route) {
        CusFavoritePage(navController)
    }
}

private fun NavGraphBuilder.showCusHistory(navController: NavController) {
    composable(CustomerLeafScreen.History.route) {
        CusHistoryPage(navController)
    }
}

private fun NavGraphBuilder.showCusPayment(navController: NavController) {
    composable(CustomerLeafScreen.Payment.route + "/{roomId}") {
        val roomId = it.arguments?.getString("roomId")?.toInt() ?: 0
        CusPaymentScreen(roomId = roomId, onBack = {
            navController.navigateUp()
        })

    }
}

// -------------- End of Customer navigation ------------------- //

// -------------- Moderator navigation ------------------- //
private fun NavGraphBuilder.addModeratorRoute(navController: NavController) {
    navigation(
        route = RootScreen.Moderator.route,
        startDestination = ModeratorLeafScreen.Home.route
    ) {
        showModHome(navController)
        showModRoom(navController)
        showModRoomAdd(navController)
        showModNotifications(navController)
        showModProfile(navController)
    }
}

private fun NavGraphBuilder.showModHome(navController: NavController) {
    composable(ModeratorLeafScreen.Home.route) {
        ModHomePage(navController = navController)
    }
}

private fun NavGraphBuilder.showModRoom(navController: NavController) {
    composable(ModeratorLeafScreen.Room.route) {
        ModRoomPage(navController = navController)
    }
}

private fun NavGraphBuilder.showModRoomAdd(navController: NavController) {
    composable(ModeratorLeafScreen.RoomAdd.route) {
        ModRoomAdd(onBack = { navController.navigateUp() })
    }
}

private fun NavGraphBuilder.showModNotifications(navController: NavController) {
    composable(ModeratorLeafScreen.Notification.route) {
        ModNotificationPage(navController)
    }
}

private fun NavGraphBuilder.showModProfile(navController: NavController) {
    composable(ModeratorLeafScreen.Profile.route) {
        ModProfilePage(navController = navController)
    }
}
// -------------- End of Moderator navigation ------------------- //