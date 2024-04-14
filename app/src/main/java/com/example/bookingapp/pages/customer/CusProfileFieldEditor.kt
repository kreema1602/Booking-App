package com.example.bookingapp.pages.customer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.mock_data.AccountData

@Composable
fun CusProfileFieldEditor(accId: Int, onBack: () -> Unit) {
    val accTmp = AccountData.sampleData.find { it.id == accId }!!
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
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        TextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("Username") },
<<<<<<< Updated upstream
            modifier = Modifier.padding(16.dp)
=======
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
>>>>>>> Stashed changes
        )
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
<<<<<<< Updated upstream
            modifier = Modifier.padding(16.dp)
=======
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
>>>>>>> Stashed changes
        )
        TextField(
            value = phone.value,
            onValueChange = { phone.value = it },
            label = { Text("Phone") },
<<<<<<< Updated upstream
            modifier = Modifier.padding(16.dp)
=======
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
>>>>>>> Stashed changes
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