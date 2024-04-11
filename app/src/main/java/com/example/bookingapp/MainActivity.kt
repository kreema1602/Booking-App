package com.example.bookingapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.bookingapp.core.compose.CalendarIcon
import com.example.bookingapp.core.compose.HomeIcon
import com.example.bookingapp.core.compose.HotelIcon
import com.example.bookingapp.core.compose.NotificationIcon
import com.example.bookingapp.core.compose.ProfileIcon
import com.example.bookingapp.core.ui.theme.BookingAppTheme
import com.example.bookingapp.navigation.AppNavGraph
import com.example.bookingapp.navigation.CustomerLeafScreen
import com.example.bookingapp.navigation.ModeratorLeafScreen
import com.example.bookingapp.navigation.RootScreen
import com.example.bookingapp.pages.LoginPage

class MainActivity : ComponentActivity() {
    private val isLoggedIn = mutableStateOf(false)
    private val userRole = mutableStateOf("guest")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BookingAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(isLoggedIn, userRole)
                }
            }
        }
    }
}


@Composable
fun MyApp(isLoggedIn: MutableState<Boolean>, userRole: MutableState<String>) {
    val navController = rememberNavController()
    val currentSelectedPage by navController.currentScreenAsState()
    val currentRoute by navController.currentRouteAsState()
    lateinit var currentRouteListByRole: List<String>

    when (userRole.value) {
        "customer" -> {
            currentRouteListByRole = listOf(
                CustomerLeafScreen.Home.route,
                CustomerLeafScreen.Reservation.route,
                CustomerLeafScreen.Notification.route,
                CustomerLeafScreen.Profile.route
            )
        }
        "moderator" -> {
            currentRouteListByRole = listOf(
                ModeratorLeafScreen.Home.route,
                ModeratorLeafScreen.Room.route,
                ModeratorLeafScreen.Notification.route,
                ModeratorLeafScreen.Profile.route
            )
        }
        else -> {
            currentRouteListByRole = listOf()
        }
    }

    Scaffold(
        bottomBar = {
            Log.d("AppNavGraph", "currentRoute: $currentRoute")
            if (currentRoute in currentRouteListByRole) {
                BottomNavBar(navController, currentSelectedPage, currentRouteListByRole)
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            AppNavGraph(navController = navController, role = userRole, isLoggedIn = isLoggedIn)
        }
    }
}

@Composable
private fun BottomNavBar(
    navController: NavController,
    currentSelectedScreen: String,
    listRoute: List<String>
) {
    @Composable
    fun getIcon(index: Int) {
        return when (index) {
            0 -> { HomeIcon() }
            1 -> { if (listRoute[1] === CustomerLeafScreen.Reservation.route) CalendarIcon() else HotelIcon() }
            2 -> { NotificationIcon() }
            3 -> { ProfileIcon() }
            else -> { HomeIcon() } // Provide a default icon
        }
    }

    val listLabel = listOf(
        stringResource(id = R.string.home),
        stringResource(id = if (listRoute[1] === CustomerLeafScreen.Reservation.route) R.string.reservations else R.string.hotel),
        stringResource(id = R.string.notifications),
        stringResource(id = R.string.profile)
    )

    NavigationBar {
        listRoute.forEachIndexed { index, route ->
            NavigationBarItem(
                selected = currentSelectedScreen == route,
                onClick = { navController.navigate(route) },
                alwaysShowLabel = true,
                label = { Text(text = listLabel[index]) },
                icon = { getIcon(index) }
            )
        }
    }
}

@Stable
@Composable
private fun NavController.currentScreenAsState(): MutableState<String> {
    val selectedItem = remember { mutableStateOf("")}
    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            Log.d("Navigation", "Destination changed to: ${destination.route}")
            selectedItem.value = destination.route.toString()
        }
        addOnDestinationChangedListener(listener)
        onDispose { removeOnDestinationChangedListener(listener) }
    }
    return selectedItem
}

@Stable
@Composable
private fun NavController.currentRouteAsState(): State<String?> {
    val selectedItem = remember { mutableStateOf<String?>(null) }
    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            Log.d("Navigation", "Destination: ${destination.route}")
            selectedItem.value = destination.route
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }
    return selectedItem
}

private fun NavController.navigateToRootScreen(rootScreen: RootScreen) {
    Log.d("AppNavGraph", "navigateToRootScreen: $rootScreen")
    navigate(rootScreen.route) {
        launchSingleTop = true
        restoreState = true
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    BookingAppTheme {
//        MyApp(role = "customer")
//    }
//}