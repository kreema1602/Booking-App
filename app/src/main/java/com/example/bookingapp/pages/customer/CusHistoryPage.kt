package com.example.bookingapp.pages.customer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookingapp.core.compose.MySpacer
import com.example.bookingapp.core.compose.TopAppBar
import com.example.bookingapp.core.ui.ThemedPreview
import com.example.bookingapp.mock_data.RoomData
import com.example.bookingapp.models.Room
import com.example.bookingapp.navigation.CustomerLeafScreen

@Composable
fun CusHistoryPage(navController: NavController) {
    LazyColumn {
        item {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TopAppBar(title = "View History", onClick = { navController.popBackStack() })
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
                RoomItem(RoomData.data[0], viewDetail = { navController.navigate(CustomerLeafScreen.RoomDetail.route + "/1") })
            }
            item {
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
            }
        }
    }
}

@Composable
fun RoomItem(item: Room, viewDetail: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 5.dp, horizontal = 16.dp)
            .clickable(onClick = viewDetail),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image
            val imageSize = 100.dp
            val imageModifier = Modifier
                .size(imageSize, imageSize + 30.dp)
                .clip(RoundedCornerShape(6.dp))
            Box(
                modifier = imageModifier
            ) {
                Image(
                    painter = painterResource(id = item.images[0]),
                    contentDescription = null, // Add proper content description
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.fillMaxSize()
                )
            }
            RoomItemDetail(item, imageSize + 30.dp)
        }

    }
}

@Composable
fun RoomItemDetail(item: Room, columnHeight: Dp) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp)
            .fillMaxWidth()
            .heightIn(min = columnHeight),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Haley House", // Hotel name
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            fontSize = 16.sp
        )
        Text(text = "${item.name} (${item.type})", color = Color(0xFF555555))
        Text(
            text = "400.000 VNĐ",
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF6400),
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview
@Composable
fun PreviewCusHistoryPage() {
    val navController = rememberNavController()
    ThemedPreview {
        CusHistoryPage(navController)
    }
}
