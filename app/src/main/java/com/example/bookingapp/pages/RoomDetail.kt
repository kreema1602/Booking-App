package com.example.bookingapp.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.core.compose.Carousel
import com.example.bookingapp.core.compose.ExpandableText
import com.example.bookingapp.core.compose.FacilityList
import com.example.bookingapp.core.compose.MySpacer
import com.example.bookingapp.core.ui.ThemedPreview
import com.example.bookingapp.core.compose.TopAppBar
import com.example.bookingapp.mock_data.RoomData
import com.example.bookingapp.core.ui.theme.OrangePrimary

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RoomDetail() {
    val room = RoomData.data[0]
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        TopAppBar(title = "Detail", onClick = {})
        Carousel(itemList = room.images)
        Text(
            text = room.name + " (" + room.type + ")",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight(650),
                fontSize = 26.sp
            ),
        )
        FacilityList()
        // Room description
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Description", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(700)))
            ExpandableText(text = room.desc, maxLines = 2, color = OrangePrimary, onClick = {})
        }

        MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
    }
}

@Composable
fun PaymentInformation() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Payment Information",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight(650),
                fontSize = 26.sp
            ),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Per night",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight(700),
                    fontSize = 20.sp
                ),
            )
            Text(
                text = "$100",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight(700),
                    fontSize = 20.sp
                ),
            )
        }
    }
}


@Preview
@Composable
fun RoomDetailPreview() {
    ThemedPreview {
//        RoomDetail()
        PaymentInformation()
    }
}