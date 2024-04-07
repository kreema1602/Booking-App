package com.example.bookingapp.core.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableText(
    text: String,
    maxLines: Int,
    color: Color,
    onClick: () -> Unit
) {
    val isExpanded = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = text,
            maxLines = if (isExpanded.value) Int.MAX_VALUE else maxLines,
            overflow = TextOverflow.Ellipsis,
        )
        val actionText = if (isExpanded.value) "Read less" else "Read more"
        Text(
            text = actionText,
            color = color,
            modifier = Modifier
                .padding(top = 4.dp)
                .align(Alignment.Start)
                .clickable { isExpanded.value = !isExpanded.value }
        )
    }
}