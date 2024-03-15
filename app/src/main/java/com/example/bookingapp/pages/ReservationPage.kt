package com.example.bookingapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookingapp.core.compose.FilledButton

@Composable
fun ReservationPage() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Reservation",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.headlineMedium
        )
        FilledButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 100.dp),
            text = "Go to detail",
            onClick = { }
        )

    }
}

@Preview
@Composable
fun ReservationPagePreview() {
    ReservationPage()
}