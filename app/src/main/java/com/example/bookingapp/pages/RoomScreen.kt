package com.example.bookingapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bookingapp.R
import com.example.bookingapp.core.compose.ExpandableText
import com.example.bookingapp.core.ui.ThemedPreview
import com.example.bookingapp.models.Hotel

@Composable
fun RoomScreen(hotel: Hotel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HotelImage(imageUrl = hotel.imageUrl)
        MySpacer(height = 8.dp)
        HotelInfo(hotel = hotel)
        MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
        HotelFacilities(facilities = hotel.facilities)
    }
}

@Composable
fun MySpacer(height: Dp, color: Color = Color.Transparent) {
    Box(
        modifier = Modifier
            .height(height)
            .fillMaxWidth()
            .background(color)
    )
}

@Composable
fun HotelImage(imageUrl: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(0.dp))
    ) {
        Image(
            //        painter = rememberAsyncImagePainter(model = imageUrl),
            painter = painterResource(id = R.drawable.hotel2),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun HotelInfo(hotel: Hotel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = hotel.name,
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
            )
            // Icon for rating
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(4.dp),
                    tint = Color.Yellow
                )
                Text(
                    text = "4.5",
                    style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                )
            }
        }
        Text(
            text = hotel.address,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
        )
        ExpandableText(
            text = hotel.desc,
            maxLines = 4,
            onClick = {},
            color = com.example.bookingapp.core.ui.theme.OrangePrimary
        )
    }
}

@Composable
fun HotelFacilities(facilities: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
            )
    ) {
        Text(
            text = "Hotel facilities",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
        )
        Row{
            facilities.forEach { facility ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(
                        end = 24.dp,
                        bottom = 8.dp,
                        top = 8.dp
                    )
                ) {
                    val icon = when (facility) {
                        "Free Wi-Fi" -> R.drawable.ic_wifi
                        "Parking" -> R.drawable.ic_parking
                        "Swimming" -> R.drawable.ic_swimming_pool
                        "Gym" -> R.drawable.ic_gym
                        else -> R.drawable.ic_wifi
                    }
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = facility,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RoomList(title: String) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun RoomListItem() {
    
}


@Preview
@Composable
fun PreviewRoomScreen() {
    ThemedPreview {
//        RoomScreen(
//            Hotel(
//                1,
//                "Hotel ABC",
//                4.5f,
//                "123 Main St, San Francisco, CA",
//                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
//                "https://developer.android.com/images/brand/Android_Robot.png",
//                listOf("Free Wi-Fi", "Parking", "Swimming", "Gym")
//            )
//        )

        RoomList("Standard")
    }
}
