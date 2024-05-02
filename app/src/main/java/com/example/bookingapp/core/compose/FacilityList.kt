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
import com.example.bookingapp.R
import com.example.bookingapp.mock_data.RoomData

@Composable
fun FacilityList() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 4.dp)
//    ) {
//        data class Facility(val type: String, val value: String)
//
//        val facilities = RoomData.data[0].facilities
//        // Loop through facilities
//        facilities.forEach { facility ->
//            val icon = when (facility.first) {
//                "Area" -> R.drawable.ic_zoom_out
//                "Bed" -> R.drawable.ic_bed
//                "Capacity" -> R.drawable.ic_person
//                "Bathroom" -> R.drawable.ic_bathroom
//                else -> R.drawable.ic_zoom_out
//            }
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.padding(end = 8.dp)
//            ) {
//                Icon(
//                    painter = painterResource(id = icon),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(30.dp)
//                        .padding(end = 4.dp, top = 4.dp, bottom = 4.dp),
//                    tint = Color.Black
//                )
//                Text(
//                    text = facility.second,
//                    style = MaterialTheme.typography.bodyMedium,
//                )
//            }
//        }
//    }
}