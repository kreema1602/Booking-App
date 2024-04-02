package com.example.bookingapp.pages.hotelier

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Surface
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.core.compose.EditCarousel
import com.example.bookingapp.core.compose.EditText
import com.example.bookingapp.core.compose.MyDropdownMenu
import com.example.bookingapp.core.compose.MySpacer
import com.example.bookingapp.core.compose.TopAppBar
import com.example.bookingapp.core.ui.theme.OrangePrimary
import com.example.bookingapp.mock_data.RoomData

@Composable
fun ModRoomEdit(onBack: () -> Unit, roomId: Int) {
    val room = RoomData.data[roomId]
    Surface {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            item {
                // Room details
                TopAppBar(title = "Detail", onClick = onBack)
                // EditCarousel(initialItems = room.images)
                EditCarousel(initialItems = room.images)
            }
            item {
                Text(
                    text = "Room",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight(700),
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )

                TextField(
                    value = room.name,
                    onValueChange = { room.name = it },
                )
            }
            item {
                Text(
                    text = "Type",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight(700)
                    ),
                    fontSize = 20.sp
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    MyDropdownMenu(
                        items = listOf("Deluxe", "Standard", "Superior", "Suite"),
                        onItemSelected = {}
                    )
                }
            }
            item {
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight(700),
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )
                TextField(
                    value = room.desc,
                    onValueChange = { room.desc = it },
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Price per night",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.fillMaxWidth(0.4f)
                    )
                    EditText(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        hint = "Price in VND"
                    )
                }
            }
            item {
                Row {
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary)
                    ) {
                        Text(text = "Save")
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text(text = "Delete Room")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewModRoomEdit() {
    ModRoomEdit(onBack = {}, roomId = 0)
}