package com.example.bookingapp.pages.hotelier

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookingapp.R
import com.example.bookingapp.core.compose.BottomSection
import com.example.bookingapp.core.compose.ExpandableText
import com.example.bookingapp.core.compose.FacilityList
import com.example.bookingapp.core.compose.FilledClipButton
import com.example.bookingapp.core.compose.MySpacer
import com.example.bookingapp.core.compose.RatingBar
import com.example.bookingapp.core.ui.theme.OrangePrimary
import com.example.bookingapp.core.ui.theme.SuccessPrimary
import com.example.bookingapp.core.ui.theme.SuccessSecondary
import com.example.bookingapp.core.ui.theme.WarningPrimary
import com.example.bookingapp.core.ui.theme.WarningSecondary
import com.example.bookingapp.mock_data.HotelData
import com.example.bookingapp.models.Hotel
import com.example.bookingapp.navigation.ModeratorLeafScreen
import kotlin.random.Random

@Composable
fun ModRoomPage(navController: NavController) {
    // Call api

    val hotel = HotelData.data.find { it.id == 1 }
    if(hotel == null) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Hotel not found",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        HotelDetail(hotel = hotel, navController = navController)
    }
}
@Composable
fun HotelDetail(hotel: Hotel, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn (
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                HotelImageContainer(hotel.imageUrl)
                MySpacer(height = 8.dp)
                HotelInfo(hotel = hotel)
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
                HotelFacilities(facilities = hotel.facilities)
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
                RoomList(title = "Standard Rooms", navController = navController, showRoomEdit = {
                     navController.navigate(ModeratorLeafScreen.RoomEdit.route + "/$it")
                })
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
                CommentsList()
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
            }
        }
    }
}
@Composable
fun HotelImageContainer(imgUrl: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(0.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.hotel2),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )
    }
}
@Composable
fun HotelInfo (hotel: Hotel) {
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
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
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
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
        Text(
            text = hotel.address,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
        )
        ExpandableText(
            text = hotel.desc,
            maxLines = 4,
            onClick = {},
            color = OrangePrimary
        )
    }
}

@Composable
fun HotelFacilities(facilities : List<String>) {
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
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
        )
        Row {
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
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RoomList(title : String, showRoomEdit : (Int) -> Unit, navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilledClipButton(
                text = "Add Room",
                onClick = { navController.navigate("mod_room_add") },
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .width(120.dp)
            )
            FilledClipButton(
                text = "Remove",
                onClick = { navController.navigate("mod_room_remove") },
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .width(120.dp)
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
            )
        )
        LazyRow(
            content = {
                items(5) {
                    RoomListItem (showRoomEdit)
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomListItem(showRoomEdit: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .width(320.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        onClick = { showRoomEdit(123) }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .width(320.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hotel2),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .background(Color.White)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Standard Room",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.Black,
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            text = "123.000 VND / night",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 14.sp
                            ),
                            color = Color.Black,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
//                    FacilityList()
                }
            }
        }
    }
}
@Composable
fun CommentsList() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "4.8",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp
                ),
                modifier = Modifier.padding(end = 8.dp)
            )
            Column {
                Text(
                    text = "Rating", style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(text = "520 reviews", style = MaterialTheme.typography.bodyMedium)
            }
        }
        repeat(5) {
            Comment()
        }
    }
}

@Composable
fun Comment() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "John Doe",
                color = Color(0xFF888888),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
            )
            RatingBar(rating = 4.5, starsColor = Color.Yellow)
        }
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        MySpacer(height = 0.5.dp, color = Color.Black)
    }
}

@Preview
@Composable
fun PreviewModRoomPage() {
    ModRoomPage(navController = rememberNavController())
}
