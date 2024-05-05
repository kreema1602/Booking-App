package com.example.bookingapp.pages.hotelier

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookingapp.core.compose.NotiCard
import com.example.bookingapp.models.Notification
import com.example.bookingapp.view_models.MainViewModel.notiViewModel

@Composable
fun ModNotificationPage(navController: NavController) {
    var notificationList: List<Notification> by remember {
        mutableStateOf(emptyList<Notification>())
    }

    LaunchedEffect(key1 = Unit) {
        notificationList = notiViewModel.getNoti()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            // Title
            Text(
                text = "Notifications",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(notificationList.size) {
                    NotiCard(notificationList[it])
                }
            }
        }
    }
}