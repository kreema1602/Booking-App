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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.bookingapp.mock_data.RoomData
import com.example.bookingapp.core.ui.theme.OrangePrimary
import com.example.bookingapp.mock_data.PaymentData
import com.example.bookingapp.navigation.CustomerLeafScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CusRoomDetail(navController: NavController, roomId: Int, onBack: () -> Unit) {
    val room = RoomData.data[0]
    Box {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                TopAppBar(title = "Detail", onClick = onBack)
//                Carousel(itemList = room.images)
                Text(
                    text = room.name + " (Standard room)",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight(650),
                        fontSize = 26.sp
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )
//                FacilityList()
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight(500)
                    ),
                    fontSize = 20.sp
                )
                ExpandableText(
                    text = "Description of the room",
                    maxLines = 2,
                    color = OrangePrimary,
                    onClick = {}
                )
            }

            item {
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
            }

            item {
                PaymentInformation()
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
                price = "400.000",
                buttonText = "Book",
                onClick = {navController.navigate(CustomerLeafScreen.Payment.route + "/$roomId")}
            )
        }
    }
}

@Composable
fun PaymentInformation() {
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
        val payment = PaymentData.data[0]
        val total = payment.perNight * payment.nights
        PaymentDetail(Pair("Per Night", "${payment.perNight} VND"))
        PaymentDetail(Pair("From", payment.from))
        PaymentDetail(Pair("To", payment.to))
        PaymentDetail(Pair("Nights", payment.nights.toString()))
        PaymentDetail(Pair("Total", "$total VND"), OrangePrimary, FontWeight.Bold)
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