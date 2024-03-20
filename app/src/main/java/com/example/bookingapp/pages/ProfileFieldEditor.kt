package com.example.bookingapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.mock_data.AccountData

@Composable
fun ProfileFieldEditor(accId: Int, onBack: () -> Unit) {
    val accTmp = AccountData.sampleData.find { it.id == accId }!!
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "Edit Profile",
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        TextField(
            value = accTmp.username,
            onValueChange = { accTmp.username = it },
            label = { Text("Username") },
            modifier = Modifier.padding(16.dp)
        )
        TextField(
            value = accTmp.email,
            onValueChange = { accTmp.email = it },
            label = { Text("Email") },
            modifier = Modifier.padding(16.dp)
        )
        TextField(
            value = accTmp.phone,
            onValueChange = { accTmp.phone = it },
            label = { Text("Phone") },
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = {
                onBack()
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Save")
        }
    }
}