package com.example.bookingapp.pages.customer

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.core.compose.TopAppBar
import com.example.bookingapp.core.ui.mavenProFontFamily
import com.example.bookingapp.core.ui.theme.OrangePrimary
import com.example.bookingapp.mock_data.RoomData

@Composable
fun CusPaymentScreen(
    roomId: Int, onBack: () -> Unit
) {
    val room = RoomData.data[0]
    Surface {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                TopAppBar(title = "Payment", onClick = onBack)
                Image(
                    painter = painterResource(id = room.images[0]),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                )
                Text(
                    text = room.name,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = mavenProFontFamily,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Address: " + room.address,
                    fontFamily = mavenProFontFamily,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
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
                PaymentDetail(data = Pair("Room", "1"))
                PaymentDetail(data = Pair("Check-in", "2022-01-01"))
                PaymentDetail(data = Pair("Check-out", "2022-01-02"))
                PaymentDetail(data = Pair("Phone", "1234567890"))
            }
            item {
                Divider(thickness = 2.dp)
            }
            item {
                PaymentDetail(data = Pair("Total", "1000"), OrangePrimary)
            }
            item {
                Button(
                    onClick = { /*TODO*/ },
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

@Preview
@Composable
fun PreviewCusPaymentScreen() {
    CusPaymentScreen(123, onBack = {})
}