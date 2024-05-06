package com.example.bookingapp.pages.hotelier

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookingapp.R
import com.example.bookingapp.core.compose.FilledClipButton
import com.example.bookingapp.core.compose.TonalButton
import com.example.bookingapp.core.ui.theme.Grey
import com.example.bookingapp.core.ui.theme.OrangePrimary
import com.example.bookingapp.core.ui.mavenProFontFamily
import com.example.bookingapp.models.Booking
import com.example.bookingapp.view_models.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun LabelIcon(imgVector: ImageVector, text: String, modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            imageVector = imgVector,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Color.Black
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp),
            fontFamily = mavenProFontFamily,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Composable
fun ModHomePage(navController: NavController) {
    LaunchedEffect(key1 = MainViewModel.modHomeViewModel.statusNotification) {
        if (MainViewModel.modHomeViewModel.statusNotification.isNotEmpty()) {
            Toast.makeText(
                navController.context,
                MainViewModel.modHomeViewModel.statusNotification,
                Toast.LENGTH_SHORT
            ).show()
            MainViewModel.modHomeViewModel.statusNotification = ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        var selectedCategory by rememberSaveable { mutableStateOf("Verify") }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .weight(1f))

            Text(
                text = MainViewModel.authViewModel.account.hotelName,
                style = MaterialTheme.typography.titleLarge,
                fontFamily = mavenProFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            )
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
            TonalButton(onClick = {},
                modifier = Modifier
                    .widthIn(min = 32.dp)
                    .align(Alignment.CenterEnd),
                contentPadding = PaddingValues(horizontal = 8.dp),
                content = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                        tint = OrangePrimary
                    )
                })
            }
        }
        
        RoomSearchBar()
        val categories = listOf("Verify", "Check-in", "Check-out")
        CategoryBar(
            categories,
            selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )
        when (selectedCategory) {
            "Verify" -> VerifyFragment()
            "Check-in" -> CheckInFragment()
            "Check-out" -> CheckOutFragment()
        }
    }
}

@Composable
fun VerifyFragment() {
    var waitingBooking by rememberSaveable { mutableStateOf(emptyList<Booking>()) }

    LaunchedEffect(key1 = Unit) {
        try {
            getWaitingBooking()
            waitingBooking = MainViewModel.modHomeViewModel.waitingBooking
        } catch (e: Exception) {
            MainViewModel.modHomeViewModel.statusNotification = e.message.toString()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val verifyCount = waitingBooking.size
        Text(
            "Waiting for verify: $verifyCount",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = mavenProFontFamily,
            fontWeight = FontWeight.Bold,
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            waitingBooking.forEach { booking ->
                item {
                    BookingItem(booking)
                }
            }
        }
    }
}

@Composable
fun CheckInFragment() {
    var acceptedBooking by rememberSaveable { mutableStateOf(emptyList<Booking>()) }

    LaunchedEffect(key1 = Unit) {
        try {
            getAcceptedBooking()
            acceptedBooking = MainViewModel.modHomeViewModel.acceptedBooking
        } catch (e: Exception) {
            MainViewModel.modHomeViewModel.statusNotification = e.message.toString()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val checkInCount = acceptedBooking.size
        Text(
            "Waiting for verify: $checkInCount",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = mavenProFontFamily,
            fontWeight = FontWeight.Bold,
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            acceptedBooking.forEach { booking ->
                item {
                    ReservationItem(booking = booking, variant = ReservationStatus.CheckIn)
                }
            }
        }
    }
}

@Composable
fun CheckOutFragment() {
    var stayingBooking by rememberSaveable { mutableStateOf(emptyList<Booking>()) }

    LaunchedEffect(key1 = Unit) {
        try {
            getStayingBooking()
            stayingBooking = MainViewModel.modHomeViewModel.stayingBooking
        } catch (e: Exception) {
            MainViewModel.modHomeViewModel.statusNotification = e.message.toString()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val checkOutCount = stayingBooking.size
        Text(
            "Waiting for verify: $checkOutCount",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = mavenProFontFamily,
            fontWeight = FontWeight.Bold,
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            stayingBooking.forEach { booking ->
                item {
                    ReservationItem(booking = booking, variant = ReservationStatus.CheckOut)
                }
            }
        }
    }
}

@Composable
fun CustomSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    onActiveChange: (Boolean) -> Unit,
    enabled: Boolean = true,
    placeHolder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    Box(
        modifier = modifier
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .border(
                border = BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(8.dp)
            )
            .focusRequester(focusRequester)
            .onFocusChanged { onActiveChange(it.isFocused) }
            .semantics {
                isTraversalGroup = true
                traversalIndex = 0f
            }
    ) {
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            ),
            enabled = enabled,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearch(query) }),
            singleLine = true,
            modifier = Modifier
                .height(56.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { onActiveChange(it.isFocused) }
                .semantics {
                    onClick {
                        focusRequester.requestFocus()
                        true
                    }
                },
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp) // inner padding

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                        ) {
                            leadingIcon?.let { it() }
                            Spacer(modifier = Modifier.width(8.dp))
                            Box {
                                if (query.isEmpty()) {
                                    placeHolder?.let { it() }
                                }
                                innerTextField()
                            }
                        }
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                        ) {
                            trailingIcon?.let { it() }
                        }
                    }
                }
            }
        )
        LaunchedEffect(active) {
            if (!active) {
                focusManager.clearFocus()
            }
        }
    }
}

@Composable
fun RoomSearchBar() {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    val backgroundColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.White

    CustomSearchBar(
        backgroundColor = backgroundColor,
        query = text,
        onQueryChange = { text = it },
        onSearch = { active = false },
        active = active,
        onActiveChange = { active = it },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                tint = Color.Black
            )
        },
        placeHolder = {
            Text(
                text = "Search for a room",
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = mavenProFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        },
    )
    {
    }

}

@Composable
fun CategoryBar(
    categories: List<String>,
    initialSelected: String,
    onCategorySelected: (String) -> Unit
) {
    var selectedCategory by remember { mutableStateOf(initialSelected) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        categories.forEach { category ->
            FilledClipButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = category,
                onClick = {
                    selectedCategory = category
                    onCategorySelected(category)
                },
                color = if (category == selectedCategory) OrangePrimary else Grey
            )
        }
    }
}

@Composable
fun BookingItem(booking: Booking) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),

            ) {
            LabelIcon(
                imgVector = Icons.Filled.AccountBox,
                text = booking.customer,
                modifier = Modifier.padding(top = 16.dp, start = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hotel2),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxWidth(0.25f)
                        .clip(shape = RoundedCornerShape(4.dp))
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(end = 8.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = booking.room,
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = mavenProFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = OrangePrimary
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Check-in: ",
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = mavenProFontFamily,
                            fontWeight = FontWeight.Medium,
                            color = Grey
                        )
                        Text(
                            text = formatDate(booking.checkIn),
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = mavenProFontFamily,
                            fontWeight = FontWeight.Medium,
                            color = Grey
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Check-out: ",
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = mavenProFontFamily,
                            fontWeight = FontWeight.Medium,
                            color = Grey
                        )
                        Text(
                            text = formatDate(booking.checkOut),
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = mavenProFontFamily,
                            fontWeight = FontWeight.Medium,
                            color = Grey
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        FilledClipButton(
                            text = "Accept",
                            onClick = {
                                CoroutineScope(Dispatchers.IO).launch {
                                    acceptBooking(booking._id)
                                }
                            },
                            modifier = Modifier.fillMaxWidth(0.5f)
                        )
                        FilledClipButton(
                            text = "Decline",
                            onClick = {
                                CoroutineScope(Dispatchers.IO).launch {
                                    rejectBooking(booking._id)
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            color = Grey
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun ReservationItem(variant: ReservationStatus = ReservationStatus.CheckIn, booking: Booking) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.hotel2),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth(0.25f)
                    .clip(shape = RoundedCornerShape(4.dp))
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(end = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = booking.room,
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = mavenProFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = OrangePrimary
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountBox,
                        contentDescription = null,
                        tint = Color.Black
                    )
                    Text(
                        text = booking.customer,
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = mavenProFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = Grey
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        modifier = Modifier.rotate(if (variant == ReservationStatus.CheckIn) 0f else 180f),
                        contentDescription = null,
                        tint = Color.Black
                    )
                    Text(
                        text = formatDate(booking.checkIn),
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = mavenProFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = Grey
                    )
                }

                FilledClipButton(text =
                    when (variant) {
                        ReservationStatus.CheckIn -> "Check-in"
                        ReservationStatus.CheckOut -> "Check-out"
                        else -> {""}
                    },
                    onClick = {
                        when (variant) {
                            ReservationStatus.CheckIn -> CoroutineScope(Dispatchers.IO).launch {
                                checkInBooking(booking._id)
                            }
                            ReservationStatus.CheckOut -> CoroutineScope(Dispatchers.IO).launch {
                                checkOutBooking(booking._id)
                            }
                            else -> {}
                        }
                    },
                    color = OrangePrimary,
                )
            }
        }

    }
}

enum class ReservationStatus {
    Verify,
    CheckIn,
    CheckOut
}

suspend fun getWaitingBooking() {
    withContext(Dispatchers.IO) {
        MainViewModel.modHomeViewModel.loadWaitingBooking()
    }
}

suspend fun getAcceptedBooking() {
    withContext(Dispatchers.IO) {
        MainViewModel.modHomeViewModel.loadAcceptedBooking()
    }
}

suspend fun getStayingBooking() {
    withContext(Dispatchers.IO) {
        MainViewModel.modHomeViewModel.loadStayingBooking()
    }
}

suspend fun acceptBooking(bookingId: String) {
    withContext(Dispatchers.IO) {
        MainViewModel.modHomeViewModel.acceptBooking(bookingId)
    }
}

suspend fun rejectBooking(bookingId: String) {
    withContext(Dispatchers.IO) {
        MainViewModel.modHomeViewModel.rejectBooking(bookingId)
    }
}

suspend fun checkInBooking(bookingId: String) {
    withContext(Dispatchers.IO) {
        MainViewModel.modHomeViewModel.checkInBooking(bookingId)
    }
}

suspend fun checkOutBooking(bookingId: String) {
    withContext(Dispatchers.IO) {
        MainViewModel.modHomeViewModel.checkOutBooking(bookingId)
    }
}

fun formatDate(timestamp: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
    val date = inputFormat.parse(timestamp)
    return outputFormat.format(date)
}

@Preview
@Composable
fun ModHomePagePreview() {
    ModHomePage(navController = rememberNavController())
}