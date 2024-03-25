package com.example.bookingapp.core.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.R
import com.example.bookingapp.core.ui.ThemedPreview
import com.example.bookingapp.core.ui.theme.OrangePrimary

@Composable
fun TopAppBar(
    title: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp),
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TonalButton(onClick = onClick,
            modifier = Modifier
                .widthIn(min = 32.dp),
            contentPadding = PaddingValues(horizontal = 8.dp),
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = null,
                    tint = OrangePrimary
                )
            })
        Text(
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 25.sp,
                fontWeight = FontWeight(600)
            ),
            text = title,
        )
        // A placeholder for the right button
        MySpacer(height = 48.dp, width = 48.dp)
    }
}

@Preview
@Composable
fun TopBarPreview() {
    ThemedPreview {
        TopAppBar(
            title = "Title", onClick = {})
    }
}