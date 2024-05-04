package com.example.bookingapp.core.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.core.ui.theme.OrangePrimary
import com.example.bookingapp.pages.mavenProFamily

@Composable
fun HistoryCard(roomName: String, from: String, to: String, customerName: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 9.dp, shape = RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Text(
            text = roomName,
            fontFamily = mavenProFamily,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp, 8.dp, 0.dp, 8.dp),
            color = OrangePrimary,
            fontWeight = FontWeight.SemiBold,
        )

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 0.dp, bottom = 16.dp)
        ) {
            Column {
                Text(
                    text = "Check in:",
                    fontFamily = mavenProFamily,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                )
                Text(
                    text = "Check out:",
                    fontFamily = mavenProFamily,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                )
                Text(
                    text = "Customer:",
                    fontFamily = mavenProFamily,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 4.dp)
                )
            }
            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 0.dp,
                    end = 0.dp,
                    bottom = 0.dp
                )
            ) {
                Text(
                    text = from,
                    fontFamily = mavenProFamily,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                )
                Text(
                    text = to,
                    fontFamily = mavenProFamily,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                )
                Text(
                    text = customerName,
                    fontFamily = mavenProFamily,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(
                        0.dp, 0.dp, 0.dp, 4.dp
                    )
                )
            }
        }
    }
}


@Composable
@Preview
fun HistoryCardPreview() {
    HistoryCard("Room name", "From date", "To date", "Customer name")
}