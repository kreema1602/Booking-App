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
import com.example.bookingapp.models.JoyhubAccount

@Composable
fun ProfileFieldEditor(acc: JoyhubAccount, onBack: () -> Unit) {
    val acc_tmp = acc
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
            value = acc_tmp.username,
            onValueChange = { acc_tmp.username = it },
            label = { Text("Username") },
            modifier = Modifier.padding(16.dp)
        )
        TextField(
            value = acc_tmp.email,
            onValueChange = { acc_tmp.email = it },
            label = { Text("Email") },
            modifier = Modifier.padding(16.dp)
        )
        TextField(
            value = acc_tmp.phone,
            onValueChange = { acc_tmp.phone = it },
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