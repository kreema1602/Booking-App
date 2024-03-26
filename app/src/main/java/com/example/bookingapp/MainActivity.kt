package com.example.bookingapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.bookingapp.core.compose.CalendarIcon
import com.example.bookingapp.core.compose.HomeIcon
import com.example.bookingapp.core.compose.NotificationIcon
import com.example.bookingapp.core.compose.ProfileIcon
import com.example.bookingapp.core.ui.theme.BookingAppTheme
import com.example.bookingapp.navigation.AppNavGraph
import com.example.bookingapp.navigation.LeafScreen
import com.example.bookingapp.navigation.RootScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BookingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}


@Composable
fun MyApp() {
    val navController = rememberNavController()
    val currentSelectedPage by navController.currentScreenAsState()
    val currentRoute by navController.currentRouteAsState()

    Scaffold (
        bottomBar = {
            Log.d("AppNavGraph", "currentRoute: $currentRoute")
            if (currentRoute in listOf(LeafScreen.Home.route, LeafScreen.Reservations.route, LeafScreen.Notifications.route, LeafScreen.Profile.route)) {
                BottomNavBar(navController, currentSelectedPage)
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            AppNavGraph(navController = navController)
        }
    }
}

@Composable
private fun BottomNavBar(
    navController: NavController,
    currentSelectedScreen: RootScreen
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentSelectedScreen == RootScreen.Home,
            onClick = { navController.navigateToRootScreen(RootScreen.Home) },
            alwaysShowLabel = true,
            label = {
                Text(text = stringResource(id = R.string.home))
            },
            icon = {
                HomeIcon()
            }
        )
        NavigationBarItem(
            selected = currentSelectedScreen == RootScreen.Reservations,
            onClick = { navController.navigateToRootScreen(RootScreen.Reservations) },
            alwaysShowLabel = true,
            label = {
                Text(text = stringResource(id = R.string.reservations))
            },
            icon = {
                CalendarIcon()
            }
        )
        NavigationBarItem(
            selected = currentSelectedScreen == RootScreen.Notifications,
            onClick = { navController.navigateToRootScreen(RootScreen.Notifications) },
            alwaysShowLabel = true,
            label = {
                Text(text = stringResource(id = R.string.notifications))
            },
            icon = {
                NotificationIcon()
            }
        )
        NavigationBarItem(
            selected = currentSelectedScreen == RootScreen.Profile,
            onClick = { navController.navigateToRootScreen(RootScreen.Profile) },
            alwaysShowLabel = true,
            label = {
                Text(text = stringResource(id = R.string.profile))
            },
            icon = {
                ProfileIcon()
            }
        )
    }
}

@Stable
@Composable
private fun NavController.currentScreenAsState(): State<RootScreen> {
    val selectedItem = remember { mutableStateOf<RootScreen>(RootScreen.Home) }
    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            Log.d("Navigation", "Destination changed to: ${destination.route}")
            when {
                destination.hierarchy.any { it.route == RootScreen.Home.route } -> {
                    selectedItem.value = RootScreen.Home
                }
                destination.hierarchy.any { it.route == RootScreen.Reservations.route } -> {
                    selectedItem.value = RootScreen.Reservations
                }
                destination.hierarchy.any { it.route == RootScreen.Notifications.route } -> {
                    selectedItem.value = RootScreen.Notifications
                }
                destination.hierarchy.any { it.route == RootScreen.Profile.route } -> {
                    selectedItem.value = RootScreen.Profile
                }
            }
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BookingAppTheme {
        MyApp()
    }
}