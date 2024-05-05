package com.example.bookingapp.pages.customer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.core.ui.theme.OrangePrimary
import com.example.bookingapp.mock_data.AccountData
import com.example.bookingapp.pages.hotelier.mavenProFamily

@Composable
fun CusProfileFieldEditor(accId: Int, onBack: () -> Unit) {
    val accTmp = AccountData.sampleData.find { it._id == accId.toString() }!!
    val phone = remember {
        mutableStateOf(accTmp.phone)
    }
    val username = remember {
        mutableStateOf(accTmp.username)
    }
    val email = remember {
        mutableStateOf(accTmp.email)
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "Edit Profile",
            fontSize = 24.sp,
            fontFamily = mavenProFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        TextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("Username") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        TextField(
            value = phone.value,
            onValueChange = { phone.value = it },
            label = { Text("Phone") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        Button(
            onClick = {
                onBack()
            },
            colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, bottom = 10.dp)
                .height(50.dp)
        ) {
            Text(
                text = "Save",
                fontFamily = mavenProFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}