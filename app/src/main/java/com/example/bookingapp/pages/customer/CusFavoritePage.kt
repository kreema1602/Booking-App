package com.example.bookingapp.pages.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookingapp.core.compose.MySpacer
import com.example.bookingapp.core.compose.TopAppBar
import com.example.bookingapp.core.ui.ThemedPreview
import com.example.bookingapp.mock_data.RoomData
import com.example.bookingapp.navigation.CustomerLeafScreen

@Composable
fun CusFavoritePage(navController: NavController) {
    LazyColumn {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TopAppBar(title = "Favorite List", onClick = { navController.popBackStack() })
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            ) {
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
            }
        }
        repeat(10) {
            item {
                RoomItem(
                    RoomData.data[0],
                    viewDetail = { navController.navigate(CustomerLeafScreen.RoomDetail.route + "/1") })
            }
            item {
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
            }
        }
    }
}

@Preview
@Composable
fun PreviewCusFavoritePage() {
    val navController = rememberNavController()
    ThemedPreview {
        CusFavoritePage(navController)
    }
}
