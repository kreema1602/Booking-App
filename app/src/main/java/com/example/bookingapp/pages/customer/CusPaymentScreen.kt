package com.example.bookingapp.pages.customer

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bookingapp.MainActivity
import com.example.bookingapp.R
import com.example.bookingapp.core.compose.TopAppBar
import com.example.bookingapp.core.compose.getFormattedDate
import com.example.bookingapp.core.ui.mavenProFontFamily
import com.example.bookingapp.core.ui.theme.OrangePrimary
import com.example.bookingapp.mock_data.RoomData
import com.example.bookingapp.models.requests.BookingRequest
import com.example.bookingapp.models.requests.UpdateAccountRequest
import com.example.bookingapp.view_models.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CusPaymentScreen(
    onBack: () -> Unit
) {
    val room = MainViewModel.cusHotelRoomViewModel.room.filter { it._id == MainViewModel.cusHotelRoomViewModel.selectedRoomId }[0]
    val pricePerNight = MainViewModel.cusHotelRoomViewModel.room.filter { it._id == MainViewModel.cusHotelRoomViewModel.selectedRoomId }[0].price
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
    val imgUrl = if (room.image[0].contains("http")) room.image[0] else room.image[0].toInt()
    Surface {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                TopAppBar(title = "Payment", onClick = onBack)

                AsyncImage(
                    model = imgUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    contentDescription = null,
                    error = painterResource(id = R.drawable.placeholder),
                    placeholder = painterResource(id = R.drawable.placeholder)
                )
                Text(
                    text = room.name,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = mavenProFontFamily,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            item {
                Divider(thickness = 2.dp)
            }
            item {
                Text(
                    text = "Booking Information",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    fontFamily = mavenProFontFamily,
                    fontSize = 20.sp,
                    color = OrangePrimary
                )
                PaymentDetail(data = Pair("Room", room.name))
                PaymentDetail(data = Pair("Check-in", getFormattedDate(MainViewModel.cusHotelRoomViewModel.checkIn)))
                PaymentDetail(data = Pair("Check-out", getFormattedDate(MainViewModel.cusHotelRoomViewModel.checkOut)))
            }
            item {
                Divider(thickness = 2.dp)
            }
            item {
                PaymentDetail(data = Pair("Total", "${pricePerNight * nights}"), OrangePrimary)
            }
            item {
                Button(
                    onClick = {
                        CoroutineScope(Dispatchers.Main).launch {
                            makeBooking(
                                MainViewModel.cusHotelRoomViewModel.selectedHotelId,
                                MainViewModel.cusHotelRoomViewModel.selectedRoomId,
                                MainViewModel.authViewModel.account._id,
                                MainViewModel.cusHotelRoomViewModel.checkIn,
                                MainViewModel.cusHotelRoomViewModel.checkOut
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary)
                ) {
                    Text(text = "Book")
                }
            }
        }
    }
}

@Composable
fun PaymentDetail(
    data: Pair<String, String>,
    color: Color = Color.Black
) {
    val fontWeight = FontWeight.Normal
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

suspend fun makeBooking(
    hotel: String,
    room: String,
    customer: String,
    checkIn: Long,
    checkOut: Long
) {
    val bookingRequest = BookingRequest().apply {
        this.hotel = hotel
        this.room = room
        this.customer = customer
        this.check_in = checkIn
        this.check_out = checkOut
    }
    val result = withContext(Dispatchers.IO) {
        try {
            MainViewModel.bookingViewModel.booking(bookingRequest)
        } catch (e: Exception) {
            throw Exception("CusPaymentScreen: ${e.message}")
        }
    }

    if (result) {
        Toast.makeText(MainActivity.context, "Booking successfully", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(MainActivity.context, "Booking failed", Toast.LENGTH_SHORT).show()
    }
}

//@Preview
//@Composable
//fun PreviewCusPaymentScreen() {
//    CusPaymentScreen("123", onBack = {})
//}