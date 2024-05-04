package com.example.bookingapp.pages.hotelier

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookingapp.R
import com.example.bookingapp.core.compose.ExpandableText
import com.example.bookingapp.core.compose.FilledClipButton
import com.example.bookingapp.core.compose.MySpacer
import com.example.bookingapp.core.ui.theme.OrangePrimary
import com.example.bookingapp.mock_data.HotelData
import com.example.bookingapp.models.Hotel
import com.example.bookingapp.navigation.RootScreen
import com.example.bookingapp.view_models.MainViewModel


@Composable
fun ModProfilePage(navController: NavController) {
    // Call api to get the des
    var hotel = remember { mutableStateOf(HotelData.data[0]) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        HotelBackground(hotel.value.imageUrl)
        LazyColumn(
            modifier = Modifier
                .padding(top = 200.dp)
                .fillMaxSize()
//                .background(Color.Cyan)
        ) {
            item {
                HotelIntro(hotel = hotel)
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
                HotelDescription(hotel = hotel.value)
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
                HotelAccount(hotel = hotel.value)
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
                OtherPart(text = "Financial Account")
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
                OtherPart(text = "Withdraw JoyCoin")
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
                OtherPart(text = "Booking History", onClick = {
                    navController.navigate("mod_history")
                })
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
                Logout(navController)
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
            }
        }
    }
}

@Composable
fun HotelBackground(imgUrl: String) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.hotel2),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomEnd),
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)

                    .background(Color.White, shape = CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_image),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Center)
                        .clickable { }
                )
            }
        }
    }
}

@Composable
fun HotelIntro(hotel: MutableState<Hotel>) {
    var isEditing by remember { mutableStateOf(false) }
    var editedName by remember { mutableStateOf(hotel.value.name) }
    var editedAddress by remember { mutableStateOf(hotel.value.address) }
    var editedDescription by remember { mutableStateOf(hotel.value.desc) }
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = hotel.value.name,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_pencil),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        // Open edit dialog
                        isEditing = true
                    }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = null,
                tint = OrangePrimary,
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = "Location:",
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .width(80.dp)
            )
            Text(
                text = hotel.value.address,
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star_filled),
                contentDescription = null,
                tint = Color(0xFFFFC819),
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = "Rating:",
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .width(80.dp)
            )
            Text(
                text = hotel.value.rating.toString(),
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        }
        if (isEditing) {
            EditDialog(
                editedName = editedName,
                editedAddress = editedAddress,
                editedDescription = editedDescription,
                onDismiss = { isEditing = false },
                onSave = {
                    // Call api to save the edited data
                    isEditing = false
                }
            )
        }
    }
}

@Composable
fun HotelDescription(hotel: Hotel) {
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
            text = "Description",
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = OrangePrimary
            ),
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
fun HotelAccount(hotel: Hotel) {
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
            text = "Account",
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = OrangePrimary
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Username:",
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .width(100.dp)
            )
            Text(
                text = "trangiathinh",
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Phone:",
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .width(100.dp)
            )
            Text(
                text = "091231992",
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Email:",
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .width(100.dp)
            )
            Text(
                text = "cokakakaka@gmail.com",
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun OtherPart(text: String, onClick: () -> Unit = { }) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight(
                    500
                )
            ),
        )
    }
}

@Composable
fun Logout(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { MainViewModel.authViewModel.logout() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Logout",
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight(
                    500
                )
            ),
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_logout),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(30.dp)
        )

    }
}

@Composable
fun EditDialog(
    editedName: String,
    editedAddress: String,
    editedDescription: String,
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(480.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(
                    text = "Edit Hotel Details",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                InputField(
                    "Hotel name",
                    "Enter hotel name",
                    remember { mutableStateOf(editedName) })
                InputField(
                    "Hotel address",
                    "Enter hotel address",
                    remember { mutableStateOf(editedAddress) })
                InputField(
                    "Description",
                    "Enter Description",
                    remember { mutableStateOf(editedDescription) })
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FilledClipButton(
                        text = "Save",
                        onClick = onSave,
                        modifier = Modifier
                            .width(120.dp)
                    )
                    FilledClipButton(
                        text = "Cancel",
                        onClick = onDismiss,
                        modifier = Modifier
                            .width(120.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun InputField(
    label: String,
    hint: String,
    fieldInput: MutableState<String>,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    fontSize: TextUnit = androidx.compose.material3.MaterialTheme.typography.bodyMedium.fontSize
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 0.dp)
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 0.dp),
        )
        TextField(
            value = fieldInput.value,
            onValueChange = { fieldInput.value = it },
            placeholder = { Text(text = hint) },
            singleLine = (label != "Description"),
            keyboardOptions = keyboardOptions,
            shape = androidx.compose.material3.MaterialTheme.shapes.small,
            textStyle = TextStyle(fontSize = fontSize),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 120.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = androidx.compose.material3.MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = androidx.compose.material3.MaterialTheme.colorScheme.surfaceVariant
            ),
        )
    }
}

@Preview
@Composable
fun ModProfilePagePreview() {
    ModProfilePage(navController = rememberNavController())
//    EditDialog(
//        editedName = "Hotel ABC",
//        editedAddress = "123 Main St, San Francisco, CA",
//        editedDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, Lorem ipsum dolor sit amet, consectetur adipiscing elit, Lorem ipsum dolor sit amet, consectetur adipiscing elit, Lorem ipsum dolor sit amet, consectetur adipiscing elit, Lorem ipsum dolor sit amet, consectetur adipiscing elit",
//        onDismiss = { /*TODO*/ },
//        onSave = {}
//    )
}

