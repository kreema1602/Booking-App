package com.example.bookingapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProfilePage() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Profile",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.headlineMedium
        )
//        FilledButton(
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .padding(top = 100.dp),
//            text = "Go to detail",
//            onClick = { showDetail() }
//        )

    }

}