package com.example.bookingapp.pages.hotelier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.R
import com.example.bookingapp.core.compose.BottomSection
import com.example.bookingapp.core.compose.EditCarousel
import com.example.bookingapp.core.compose.EditText
import com.example.bookingapp.core.compose.MyDropdownMenu
import com.example.bookingapp.core.compose.MySpacer
import com.example.bookingapp.core.compose.TopAppBar
import com.example.bookingapp.core.ui.ThemedPreview
import com.example.bookingapp.mock_data.RoomData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModRoomAdd(onBack: () -> Unit) {
    val room = RoomData.data[0]
    Box {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                TopAppBar(title = "Detail", onClick = onBack)
//                EditCarousel(initialItems = room.images)
                Text(
                    text = "Room",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight(700),
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )
                EditText(
                    hint = "Room name", modifier = Modifier
                        .padding(vertical = 8.dp)
                        .height(60.dp), singleLine = true
                )
                Text(
                    text = "Type",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight(700)
                    ),
                    fontSize = 20.sp
                )
                Box(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    MyDropdownMenu(
                        items = listOf("Deluxe", "Standard", "Superior", "Suite"),
                        onItemSelected = {}
                    )
                }
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight(700)
                    ),
                    fontSize = 20.sp
                )
                EditText(
                    hint = "Write your description here...", modifier = Modifier
                        .padding(vertical = 8.dp)
                        .heightIn(min = 120.dp), singleLine = false
                )
            }

            item {
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
            }

            item {
                DetailInputField()
            }

            item {
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
            }

            item {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Price per night", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.fillMaxWidth(0.4f))
                    EditText(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), hint = "Price in VND")
                }
            }

            item {
                MySpacer(height = 100.dp, color = Color.Transparent)
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(Color.White)
        ) {
            BottomSection(
                buttonText = "Create",
                onClick = {}
            )
        }
    }
}

@Composable
fun DetailInputField() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        val properties = listOf(
            "Area",
            "People",
            "Bed"
        )
        properties.forEach { property ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val icon = when (property) {
                    "Bed" -> R.drawable.ic_bed
                    "People" -> R.drawable.ic_person
                    else -> R.drawable.ic_zoom_out
                }
                Row(modifier = Modifier.fillMaxWidth(0.4f), verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(end = 4.dp, top = 4.dp, bottom = 4.dp),
                        tint = Color.Black
                    )
                    Text(text = property, style = MaterialTheme.typography.bodyLarge)
                }
                val hint = when (property) {
                    "Area" -> "Area in m²"
                    "People" -> "Number of people"
                    "Bed" -> "Number of beds"
                    else -> ""
                }
                EditText(hint = hint, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            }
        }
    }
}


@Preview
@Composable
fun PreviewModRoomAdd() {
    ThemedPreview {
        ModRoomAdd(onBack = {})
    }
}
