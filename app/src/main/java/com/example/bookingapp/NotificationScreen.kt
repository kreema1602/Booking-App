package com.example.bookingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.bookingapp.ui.theme.BookingAppTheme

/*
* NotiContent class
*
* param: variant: String - type of notification (booking, cancellation)
* param: hotelName: String - name of the hotel
* param: reason: String | null - reason for cancellation
 */
class NotiContent(variant: String, hotelName: String, reason: String? = null) {
    val content: String = when (variant) {
        "booking" -> "is accepted by $hotelName"
        "cancel" -> "is cancelled by $hotelName due to $reason"
        else -> "Invalid notification type"
    }
}

class NotificationScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookingAppTheme {
                NotificationContent()
            }
        }
    }
}

@Composable
fun NotiCard(
    variant: String,
    bookingId: String,
    content: NotiContent,
    updatedAt: String = "Just now",
    isRead: Boolean = false
) {
    Surface(
        shape = MaterialTheme.shapes.large,
        shadowElevation = 3.dp,
        contentColor = Color.White,
        modifier = Modifier
            .padding(bottom = 20.dp)
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(all = 16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = if (variant == "booking") R.drawable.icon_checkmark_circle else R.drawable.icon_close_circle_outline),
                contentDescription = null,
                tint = if (variant == "booking") Color(0xFF36D000) else Color(0xFFFF6400),
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)) {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("$bookingId ")
                        }
                        append(content.content)
                    },
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    maxLines = 2
                )
                Text(text = updatedAt,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.End,
                    fontSize = 16.sp,
                    fontWeight = if (isRead) FontWeight.Normal else FontWeight.Bold,
                    color = if (isRead) Color.Gray else Color(0xFFFF6400),
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }
        }
    }
}

@Composable
fun NotificationContent() {
    Surface(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            // Title
            Text(
                text = "Notifications",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            )
            for (i in 1..5) {
                // random content
                val variant = if (i % 2 == 0) "booking" else "cancel"
                val notification = if (variant == "cancel") {
                    NotiContent("cancel", "Hotel A", "unforeseen circumstances")
                } else {
                    NotiContent("booking", "Hotel A")
                }
                NotiCard(variant, "Booking ID", notification, "Just now", false)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    BookingAppTheme {
        NotificationContent()
    }
}