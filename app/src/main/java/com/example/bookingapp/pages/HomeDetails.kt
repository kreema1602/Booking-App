package com.example.bookingapp.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookingapp.core.compose.FilledButton

@Composable
fun HomeDetailsPage(onBack: () -> Unit) {
    Column (modifier = Modifier.fillMaxSize()) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
                .clickable { onBack() }
        )
        Text(
            text = "Details",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.headlineMedium
        )
        FilledButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 100.dp),
            text = "Go back",
            onClick = onBack
        )
    }
}