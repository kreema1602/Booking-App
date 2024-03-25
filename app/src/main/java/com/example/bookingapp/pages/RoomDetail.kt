package com.example.bookingapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookingapp.core.ui.ThemedPreview
import com.example.bookingapp.core.compose.TopAppBar

@Composable
fun RoomDetail() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(title = "Detail", onClick = {})
    }
}


@Preview
@Composable
fun RoomDetailPreview() {
    ThemedPreview {
        RoomDetail()
    }
}