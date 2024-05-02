package com.example.bookingapp.core.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.bookingapp.R
import com.example.bookingapp.mock_data.RoomData
import com.example.bookingapp.pages.customer.amenityMap

val facilityMap = mapOf(
    "Area" to "https://img.icons8.com/ios/24/page-size.png",
    "Bedroom" to "https://img.icons8.com/ios/24/bedroom.png",
    "Guest" to R.drawable.ic_person,
    "Bathroom" to "https://img.icons8.com/ios/24/bath.png"
)

@Composable
fun FacilityList(map: Map<String, String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp, start = 4.dp)
    ) {
        map.forEach { facility ->
            val icon = facilityMap[facility.key] ?: R.drawable.ic_person
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 12.dp)
            ) {
                AsyncImage(
                    model = facilityMap[facility.key] ?: "",
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = facility.value,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}