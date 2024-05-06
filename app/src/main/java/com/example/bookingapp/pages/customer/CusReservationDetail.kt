package com.example.bookingapp.pages.customer

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookingapp.models.ReservationItem
import com.example.bookingapp.view_models.MainViewModel
import java.text.SimpleDateFormat

@Composable
fun CusReservationDetail(reservationId: String, navController: NavController) {
    var reservation by remember { mutableStateOf(ReservationItem())}
    LaunchedEffect(key1 = reservationId) {
        reservation = MainViewModel.bookingViewModel.getDetailBooking(reservationId)
    }

    Surface(color = Color.White) {
        Column(modifier = Modifier.fillMaxSize()) {
            DetailTopBar(reservation, navController)
            DetailContent(reservation)
        }
    }
}

@Composable
fun DetailTopBar(reservation: ReservationItem, navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back Button
            IconButton(
                onClick = {
                    navController.navigateUp()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }

            // Status Box
            StatusBox(status = reservation.status)

            // Report Button (if status is Completed)
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Report",
                    tint = if (reservation.status == "Completed") Color.Black else Color.White
                )
            }


        }
    }
}

@Composable
fun DetailContent(reservation: ReservationItem) {
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    val date = sdf.parse(reservation.check_in)?.toString() + " - " + sdf.parse(reservation.check_out)?.toString()
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CoreContent(images = reservation.image.map { it.toInt() }
            , hotelName = reservation.hotel
            , roomType = reservation.roomtype)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .background(Color(0xFFe7e7e7))

        )
        Information(date = date, price = reservation.price.toString())
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .background(Color(0xFFe7e7e7))
        )
        StatusButton(status = reservation.status)
    }
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoreContent(images: List<Int>, hotelName: String, roomType: String) {
    val pagerState = rememberPagerState(pageCount = { images.size })
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 0.dp, start = 24.dp, end = 24.dp, bottom = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            HorizontalPager(
                state = pagerState,
                key = { images[it] }
            ) { index ->
                Image(
                    painter = painterResource(id = images[index]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp))
                )
            }
        }
        Text(
            text = hotelName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = roomType,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            fontSize = 22.sp,
        )
    }
}

@Composable
fun InformationRow(title: String, content: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = if (title == "Total price") FontWeight.Bold else FontWeight.Normal,
            color = if (title == "Total price") Color(0xFFFF6400) else Color.Black
        )
        Text(
            text = content,
            fontSize = 16.sp,
            fontWeight = if (title == "Total price") FontWeight.Bold else FontWeight.Normal,
            color = if (title == "Total price") Color(0xFFFF6400) else Color.Black
        )
    }
}

@Composable
fun Information(date: String, price: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 24.dp, end = 24.dp, bottom = 16.dp)
    ) {
        Text(
            text = "Information",
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
        )
        InformationRow(title = "From", content = date.split(" - ")[0])
        InformationRow(title = "To", content = date.split(" - ")[1])
        InformationRow(title = "Total night", content = "2 nights")
        InformationRow(title = "Total price", content = "$price VND")
    }
}

@Composable
fun StatusButton(status: String) {
    if (status == "Completed" || status == "Waiting") {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 22.dp, start = 24.dp, end = 24.dp, bottom = 16.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(Color(0xFFff6400))
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if(status == "Completed") "Rating" else "Cancel Reservation",
                color = Color(0xFFFFFFFF),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(vertical = 10.dp),
            )
        }
    }
}