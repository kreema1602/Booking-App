package com.example.bookingapp.pages.customer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookingapp.core.compose.BottomSection
import com.example.bookingapp.core.compose.Carousel
import com.example.bookingapp.core.compose.ExpandableText
import com.example.bookingapp.core.compose.FacilityList
import com.example.bookingapp.core.compose.MySpacer
import com.example.bookingapp.core.compose.TopAppBar
import com.example.bookingapp.core.compose.getFormattedDate
import com.example.bookingapp.core.ui.theme.OrangePrimary
import com.example.bookingapp.navigation.CustomerLeafScreen
import com.example.bookingapp.view_models.MainViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CusRoomDetail(navController: NavController, onBack: () -> Unit) {
    val room = MainViewModel.cusHotelRoomViewModel.room.filter { it._id == MainViewModel.cusHotelRoomViewModel.selectedRoomId }[0]
    val dateRangeState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = MainViewModel.cusHotelRoomViewModel.checkIn + 86400000L,
        initialSelectedEndDateMillis = MainViewModel.cusHotelRoomViewModel.checkOut + 86400000L
    )
    val nights by remember(dateRangeState) {
        derivedStateOf {
            val startDate = dateRangeState.selectedStartDateMillis ?: return@derivedStateOf 0
            val endDate = dateRangeState.selectedEndDateMillis ?: startDate
            ((endDate - startDate) / 86400000).toInt()
        }
    }
    val pricePerNight = MainViewModel.cusHotelRoomViewModel.room.filter { it._id == MainViewModel.cusHotelRoomViewModel.selectedRoomId }[0].price

    Box {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                TopAppBar(title = "Detail", onClick = onBack)
                Carousel(itemList = room.image)
                Text(
                    text = room.name + " (${room.roomType})",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight(650),
                        fontSize = 26.sp
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )
                FacilityList(mapOf(
                    "Area" to "${room.area} m²",
                    "Bedroom" to "${room.bedroom}",
                    "Guest" to "${room.guest}",
                    "Bathroom" to "${room.bathroom}"
                ))
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight(500)
                    ),
                    fontSize = 20.sp
                )
                ExpandableText(
                    text = room.description,
                    maxLines = 2,
                    color = OrangePrimary,
                    onClick = {}
                )
            }

            item {
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
            }

            item {
                PaymentInformation(dateRangeState, nights)
            }

            item {
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
            }

            item {
                Column(
                    modifier = Modifier.background(Color.Transparent)
                ) {
                    Text(
                        text = "Cancellation Policy",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight(550),
                            fontSize = 24.sp
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = "Free cancellation 1 hour before check-in time",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 16.sp
                        )
                    )
                }
            }

            item {
                MySpacer(height = 200.dp, color = Color.Transparent)
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            BottomSection(
                calendar = true,
                price = "${pricePerNight * nights}",
                buttonText = "Book",
                onClick = {navController.navigate(CustomerLeafScreen.Payment.route + "/${MainViewModel.cusHotelRoomViewModel.selectedRoomId}")},
                dateRangeState = dateRangeState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentInformation(dateRangeState: DateRangePickerState, nights: Int = 1) {
    val pricePerNight = MainViewModel.cusHotelRoomViewModel.room.filter { it._id == MainViewModel.cusHotelRoomViewModel.selectedRoomId }[0].price

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Payment Information",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight(550),
                fontSize = 24.sp
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        PaymentDetail(Pair("Per Night", "$pricePerNight VND"))
        PaymentDetail(Pair("From", getFormattedDate(dateRangeState.selectedStartDateMillis!!)))
        PaymentDetail(Pair("To", getFormattedDate(if (dateRangeState.selectedEndDateMillis != null) dateRangeState.selectedEndDateMillis!! else dateRangeState.selectedStartDateMillis!!)))
        PaymentDetail(Pair("Nights", nights.toString()))
        PaymentDetail(Pair("Total", "${pricePerNight * nights} VND"), OrangePrimary, FontWeight.Bold)
    }
}

@Composable
fun PaymentDetail(
    data: Pair<String, String>,
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = data.first,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 18.sp,
                fontWeight = fontWeight,
                color = color
            ),
        )
        Text(
            text = data.second,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 18.sp,
                fontWeight = fontWeight,
                color = color
            ),
        )
    }
}