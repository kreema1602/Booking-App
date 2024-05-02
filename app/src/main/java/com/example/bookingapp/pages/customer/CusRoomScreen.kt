package com.example.bookingapp.pages.customer

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bookingapp.R
import com.example.bookingapp.core.compose.BottomSection
import com.example.bookingapp.core.compose.ExpandableText
import com.example.bookingapp.core.compose.FacilityList
import com.example.bookingapp.core.compose.MySpacer
import com.example.bookingapp.core.compose.RatingBar
import com.example.bookingapp.core.compose.TonalButton
import com.example.bookingapp.core.ui.ThemedPreview
import com.example.bookingapp.core.ui.theme.OrangePrimary
import com.example.bookingapp.core.ui.theme.SuccessPrimary
import com.example.bookingapp.core.ui.theme.SuccessSecondary
import com.example.bookingapp.core.ui.theme.WarningPrimary
import com.example.bookingapp.core.ui.theme.WarningSecondary
import com.example.bookingapp.models.Account
import com.example.bookingapp.models.Amenity
import com.example.bookingapp.models.Room
import com.example.bookingapp.services.HotelRoomService.getHotelAmenities
import com.example.bookingapp.view_models.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun CusRoomScreen(onBack: () -> Unit, showRoomDetail: (Int) -> Unit) {
    var room by rememberSaveable { mutableStateOf(emptyList<Room>()) }
    var hotel by rememberSaveable { mutableStateOf(Account()) }

    LaunchedEffect(Unit) {
//        MainViewModel.cusHotelRoomViewModel.fetchData(MainViewModel.cusHotelRoomViewModel.selectedHotelId)
//        room = MainViewModel.cusHotelRoomViewModel.room
//        hotel = MainViewModel.cusHotelRoomViewModel.hotel
        room = getRoomData()
        hotel = getHotel()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item { HotelImage(imageUrl = hotel.image, onBack) }
            item { MySpacer(height = 8.dp) }
            item { HotelInfo(hotel = hotel) }
            item { MySpacer(height = 8.dp, color = Color(0xFFF2F2F2)) }
            item { HotelFacilities() }
            item { MySpacer(height = 8.dp, color = Color(0xFFF2F2F2)) }
//            item { RoomList(title = "Standard", showRoomDetail) }
//            item { MySpacer(height = 8.dp) }
//            item { RoomList(title = "Deluxe", showRoomDetail) }
//            item { MySpacer(height = 8.dp, color = Color(0xFFF2F2F2)) }
//            item { CommentsList() }
//            item { MySpacer(height = 100.dp, color = Color.Transparent) }
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            BottomSection(calendar = true, onClick = {})
        }
    }
}

@Composable
fun HotelImage(imageUrl: String, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(0.dp))
    ) {
        AsyncImage(
            model = imageUrl,
            error = painterResource(id = R.drawable.hotel2),
            placeholder = painterResource(id = R.drawable.placeholder),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().heightIn(max = 280.dp)
        )
        TopBar(onBack)
    }
}

@Composable
fun TopBar(onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp,
                bottom = 8.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TonalButton(
            onClick = onBack,
            modifier = Modifier
                .widthIn(min = 32.dp)
                .padding(
                    start = 8.dp,
                    top = 8.dp
                ),
            contentPadding = PaddingValues(horizontal = 8.dp),
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = null,
                    tint = OrangePrimary
                )
            }
        )
        TonalButton(
            onClick = { },
            modifier = Modifier
                .widthIn(min = 32.dp)
                .padding(
                    end = 8.dp,
                    top = 8.dp
                ),
            contentPadding = PaddingValues(horizontal = 8.dp),
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_heart_outlined),
                    contentDescription = null,
                    tint = OrangePrimary
                )
            }
        )
    }
}

@Composable
fun HotelInfo(hotel: Account) {
    var rating by remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(key1 = Unit) {
        rating = getHotelRating()
    }

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
                text = hotel.hotelName,
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
                    text = rating.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
        Text(
            text = hotel.hotelAddress,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight(500)),
            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
        )
        ExpandableText(
            text = hotel.description,
            maxLines = 4,
            onClick = {},
            color = OrangePrimary
        )
    }
}

@Composable
fun HotelFacilities() {
    var amenities by remember { mutableStateOf(emptyList<Amenity>()) }
    LaunchedEffect(key1 = Unit) {
        amenities = getHotelAmenities(MainViewModel.cusHotelRoomViewModel.selectedHotelId)
    }

    Text(
        text = "Hotel facilities",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp, start = 16.dp)
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
            )
    ) {
        items(amenities.chunked(3)) { rowAmenities ->
            Row {
                rowAmenities.forEach { amenity ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(
                            end = 24.dp,
                            bottom = 8.dp,
                            top = 8.dp
                        )
                    ) {
                        AsyncImage(
                            model = facilityMap[amenity.name]!!,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = amenity.name,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 4.dp).widthIn(max = 68.dp).align(Alignment.CenterHorizontally),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

// facility hashmap
val facilityMap = mapOf(
    "Wifi" to R.drawable.ic_wifi,
    "Pool" to R.drawable.ic_swimming_pool,
    "Towel" to "https://img.icons8.com/ios/50/towel.png",
    "CD/DVD Player" to "https://img.icons8.com/ios/24/cd--v1.png",
    "TV" to R.drawable.ic_tv,
    "Shampoo" to "https://img.icons8.com/ios/50/shampoo.png",
    "Electronic Safe/Lock" to R.drawable.ic_lock,
    "Mini Fridge" to R.drawable.ic_cold,
    "Slippers" to "https://img.icons8.com/ios/24/slippers.png",
    "Air conditioner" to "https://img.icons8.com/ios/24/air-conditioner.png",
    "Coffee Maker" to "https://img.icons8.com/ios/24/coffee-maker.png",
    "River" to "https://img.icons8.com/ios/50/creek.png"
)

@Composable
fun RoomList(title: String, showRoomDetail: (Int) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
                    RoomListItem(showRoomDetail)
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomListItem(showRoomDetail: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .width(320.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        onClick = { showRoomDetail(123) }
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
                // State
                val state = "Available"
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(43.dp)
                        .align(Alignment.TopEnd)
                        .padding(
                            top = 12.dp,
                            end = 12.dp
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .widthIn(max = 110.dp)
                            .align(Alignment.TopEnd)
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (state == "Full") WarningSecondary else SuccessSecondary)
                            .padding(
                                start = 4.dp,
                                end = 4.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Icon(
                            painter = painterResource(id = if (state == "Full") R.drawable.ic_cancel else R.drawable.ic_check),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 4.dp),
                            tint = if (state == "Full") WarningPrimary else SuccessPrimary
                        )
                        Text(
                            text = state,
                            color = if (state == "Full") WarningPrimary else SuccessPrimary,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
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
                    FacilityList()
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

suspend fun getRoomData(): List<Room> {
    return withContext(Dispatchers.IO) {
        try {
            MainViewModel.cusHotelRoomViewModel.getRoomData(MainViewModel.cusHotelRoomViewModel.selectedHotelId)
        } catch (e: Exception) {
            Log.e("CusRoomScreen", e.message.toString())
            emptyList()
        }
    }
}

suspend fun getHotel(): Account {
    return withContext(Dispatchers.IO) {
        try {
            MainViewModel.cusHotelRoomViewModel.getHotel(MainViewModel.cusHotelRoomViewModel.selectedHotelId)
        } catch (e: Exception) {
            Log.e("CusRoomScreen", e.message.toString())
            Account()
        }
    }
}

suspend fun getHotelRating(): Double {
    return withContext(Dispatchers.IO) {
        try {
            MainViewModel.cusHotelRoomViewModel.getAverageRating(MainViewModel.cusHotelRoomViewModel.selectedHotelId)
        } catch (e: Exception) {
            Log.e("CusRoomScreen", e.message.toString())
            0.0
        }
    }
}

@Preview
@Composable
fun PreviewCusRoomScreen() {
    ThemedPreview {
        CusRoomScreen(
            onBack = {},
            showRoomDetail = {}
        )
    }
}
