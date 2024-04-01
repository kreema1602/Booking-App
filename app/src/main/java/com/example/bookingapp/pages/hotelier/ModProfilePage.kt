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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.bookingapp.core.compose.ExpandableText
import com.example.bookingapp.core.compose.FilledClipButton
import com.example.bookingapp.core.compose.MySpacer
import com.example.bookingapp.core.ui.theme.OrangePrimary
import com.example.bookingapp.mock_data.HotelData
import com.example.bookingapp.models.Hotel


@Composable
fun ModProfilePage(navController: NavController) {
    // Call api to get the des
    val hotel = HotelData.data[0]
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        HotelBackground(hotel.imageUrl)
        LazyColumn (
            modifier = Modifier
                .padding(top = 200.dp)
                .fillMaxSize()
//                .background(Color.Cyan)
        ) {
            item {
                HotelIntro(hotel = hotel)
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
                HotelDescription(hotel = hotel)
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
                HotelAccount(hotel = hotel)
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
                OtherPart(text = "Financial Account")
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
                OtherPart(text = "Withdraw JoyCoin")
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
                OtherPart(text = "Booking History")
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
                Logout()
                MySpacer(height = 8.dp, color = Color(0xFFF2F2F2))
            }
        }
    }

}
@Composable
fun HotelBackground(imgUrl : String) {
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
                        .clickable {  }
                )
            }
        }
    }
}
@Composable
fun HotelIntro(hotel : Hotel) {
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
                text = hotel.name,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_pencil),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    .size(20.dp)
                    .clickable {  }
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
                text = hotel.address,
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
                text = hotel.rating.toString(),
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                color = Color.Gray
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
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, color = OrangePrimary),
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
fun HotelAccount(hotel : Hotel) {
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
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, color = OrangePrimary),
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
                text = "cokamegai@gmail.com",
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        }
        FilledClipButton(
            text = "Change password",
            onClick = {}
        )
    }
}
@Composable
fun OtherPart(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight(500)),
        )
    }
}
@Composable
fun Logout() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable {  },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Logout",
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight(500)),
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
@Preview
@Composable
fun ModProfilePagePreview() {
    ModProfilePage(navController = rememberNavController())
}