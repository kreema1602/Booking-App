package com.example.bookingapp.reservation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ReservationMainScreen() {
    Surface(color = Color.White) {
        Column(modifier = Modifier.fillMaxSize()) {
            MainTopBar(title = "Reservation")
            ReservationList(navController)
        }
    }
}

@Composable
fun MainTopBar(title: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    fontFamily = MaterialTheme.typography.h5.fontFamily
                )
            )
        }
    }
}

@Composable
fun ReservationList(navController: NavHostController) {
    val items = ReservationData.sampleData

    LazyColumn (
//        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF2F2F2))
            .padding(top = 10.dp)
    )
    {
        items(items = items) { item ->
            ReservationListItem(item = item, viewDetail = {
                Log.i("ReservationList", "View detail of ${item.id}")
                navController.navigate("reservation_detail/${item.id}")
            })
        }
    }
}
@Composable
fun ReservationListItem(item: ReservationItem, viewDetail: () -> Unit = {}){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .clickable (onClick = viewDetail),
//        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            // Image
            val imageSize = 100.dp
            val imageModifier = Modifier
                .size(imageSize, imageSize + 30.dp)
                .clip(RoundedCornerShape(6.dp))
            Box(
                modifier = imageModifier
            ) {
                Image(
                    painter = painterResource(id = item.imageResource[0]),
                    contentDescription = null, // Add proper content description
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Details
            val columnHeight = imageSize + 30.dp
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
                    .fillMaxWidth()
                    .heightIn(min = columnHeight),
//                .background(Color.Red),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                StatusBox(status = item.status)
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333),
                    fontSize = 16.sp)
                Text(text = item.roomType, color = Color(0xFF555555))
                Text(text = item.date, color = Color(0xFF555555))
                Text(
                    text = "${item.price} VNĐ",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF6400),
                    fontSize = 18.sp)

            }
        }
        // Rating Button Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (item.status == "Completed") {
                Button(
                    onClick = { /* Handle button click */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFff6400)
                    )
                ) {
                    Text(text = "Rating", color = Color(0xFFFFFFFF))
                }
            }
        }

    }
}

@Composable
fun StatusBox(status: String) {
    val backgroundColor = when (status) {
        "Completed" -> Color(0xFFC7EDD9)
        "Waiting" -> Color(0xFFFFF3EB)
        "Cancelled" -> Color(0xFFdddddd)
        else -> Color.Gray
    }

    val textColor = when (status) {
        "Completed" -> Color(0xFF41AF79)
        "Waiting" -> Color(0xFFff6400)
        "Cancelled" -> Color(0xFF878787)
        else -> Color.Gray
    }

    Box(
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(4.dp))
            .padding(horizontal = 4.dp, vertical = 1.dp)
    ) {
        Text(
            text = status,
            color = textColor,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 1.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ReservationMainScreenPreview() {
    navController = rememberNavController()
    ReservationMainScreen()
}
