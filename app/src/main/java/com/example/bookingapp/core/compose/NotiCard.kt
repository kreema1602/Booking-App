package com.example.bookingapp.core.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.R
import com.example.bookingapp.models.NotiContent

fun getIconAndColor(variant: String): Pair<Int, Color> {
    val icon = when (variant) {
        "booking" -> R.drawable.icon_checkmark_circle
        "cancel" -> R.drawable.icon_close_circle_outline
        "alert" -> R.drawable.icon_alert
        "order" -> R.drawable.icon_order
        else -> R.drawable.icon_checkmark_circle
    }
    val color = when (variant) {
        "booking" -> Color(0xFF36D000)
        "cancel" -> Color(0xFFFF6400)
        "alert" -> Color(0xFFFA0B0B)
        "order" -> Color(0xFFFFA800)
        else -> Color(0xFF36D000)
    }
    return Pair(icon, color)
}

@Composable
fun NotificationTextColumn(
    bookingId: String,
    content: NotiContent,
    updatedAt: String,
    isRead: Boolean
) {
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

@Composable
fun NotiCard(
    variant: String,
    bookingId: String,
    content: NotiContent,
    updatedAt: String = "Just now",
    isRead: Boolean = false
) {
    val (icon, color) = getIconAndColor(variant)
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
//                painter = painterResource(id = if (variant == "booking") R.drawable.icon_checkmark_circle else R.drawable.icon_close_circle_outline),
                painter = painterResource(id = icon),
                contentDescription = null,
//                tint = if (variant == "booking") Color(0xFF36D000) else Color(0xFFFF6400),
                tint = color,
                modifier = Modifier
                    .size(56.dp)
                    .clip(if (variant == "alert") MaterialTheme.shapes.medium else CircleShape)
                    .fillMaxWidth()
            )
            NotificationTextColumn(bookingId, content, updatedAt, isRead)
        }
    }
}