package com.example.bookingapp.pages.customer

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.bookingapp.MainActivity
import com.example.bookingapp.R
import com.example.bookingapp.models.Account
import com.example.bookingapp.pages.hotelier.HotelDescription
import com.example.bookingapp.services.HotelRoomService.getAverageRating
import com.example.bookingapp.services.HotelRoomService.getPriceRange
import com.example.bookingapp.view_models.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun CusHomePage(
    showRoomScreen: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 0.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(100.dp)
                    .height(50.dp)
                    .align(Alignment.Start)
            )
            SearchBar()
        }
        HotelList(showRoomScreen)
    }

}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HotelList(showRoomScreen: (String) -> Unit) {
    var hotelData by rememberSaveable { mutableStateOf(emptyList<Account>()) }
    val lazyListState = rememberLazyListState()

    LaunchedEffect(Unit) {
        hotelData += getHotelData(0)
    }

    fun isScrolledToEnd(): Boolean {
        val layoutInfo = lazyListState.layoutInfo
        return layoutInfo.visibleItemsInfo.lastOrNull()?.index == hotelData.size - 1
    }

    if (isScrolledToEnd()) {
        CoroutineScope(Dispatchers.IO).launch {
            hotelData += getHotelData(hotelData.size)
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = lazyListState
    ) {
        items(hotelData.size) { index ->
            HotelItem(showRoomScreen, hotelData[index])
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    val backgroundColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray

    Box(
        Modifier
            .background(Color.Transparent)
            .semantics { isTraversalGroup = true }) {
        Surface(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .background(Color.Transparent),
            contentColor = MaterialTheme.colorScheme.onSurface,
            color = Color.Transparent,
            shape = RoundedCornerShape(10.dp)
        ) {
            androidx.compose.material3.SearchBar(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .background(Color.Transparent)
                    .semantics { traversalIndex = -1f },
                query = text,
                onQueryChange = { text = it },
                onSearch = { active = false },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = { Text("Enter hotel name") },
                trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                shape = RoundedCornerShape(10.dp),
                colors = SearchBarDefaults.colors(backgroundColor),
            ) {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelItem(showRoomScreen: (String) -> Unit, hotel: Account) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        onClick = { showRoomScreen(hotel._id) }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = hotel.image,
                    error = painterResource(id = R.drawable.hotel2),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                HotelDescription(modifier = Modifier
                    .align(Alignment.BottomStart)
                    .background(Color.Black.copy(alpha = 0.5f)),
                    hotel)
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HotelDescription(modifier: Modifier, hotel: Account) {
    var rating by rememberSaveable { mutableDoubleStateOf(0.0) }
    var price by rememberSaveable { mutableStateOf(Pair(0.0, 0.0)) }

    LaunchedEffect(key1 = Unit) {
        rating = getAverageRating(hotel._id)
        price = getPriceRange(hotel._id)
    }
    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = hotel.hotelName,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
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
                    color = Color.White
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp),
                    tint = Color.White
                )
                Text(
                    text = hotel.hotelAddress.split(",")[0].trim(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    maxLines = 1,
                    modifier = Modifier.widthIn(max = 150.dp),
                    textAlign = TextAlign.Start
                )
            }

            Text(
                text = "Price: ${price.first}K - ${price.second}K",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}

suspend fun getHotelData(start: Int): List<Account> {
    return withContext(Dispatchers.IO) {
        try {
            MainViewModel.cusHotelRoomViewModel.getHotels(start)
        } catch (e: Exception) {
            Log.d("CusHomePage", "getHotelData: ${e.message}")
            emptyList()
        }
    }
}

suspend fun getAverageRating(hotelId: String): Double {
    return withContext(Dispatchers.IO) {
        try {
            MainViewModel.cusHotelRoomViewModel.getAverageRating(hotelId)
        } catch (e: Exception) {
            Log.d("CusHomePage", "getAverageRating: ${e.message}")
            0.0
        }
    }
}

suspend fun getPriceRange(hotelId: String): Pair<Double, Double> {
    return withContext(Dispatchers.IO) {
        try {
            MainViewModel.cusHotelRoomViewModel.getPriceRange(hotelId)
        } catch (e: Exception) {
            Log.d("CusHomePage", "getPriceRange: ${e.message}")
            Pair(0.0, 0.0)
        }
    }
}