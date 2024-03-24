package com.example.bookingapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.bookingapp.R

@Composable
fun LoginPage() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = R.drawable.img_logo,
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        AsyncImage(model = R.drawable.logo, contentDescription = null)
        Text(text = "where you can discover, book or host a room")
        var username by remember { mutableStateOf("Enter username") }
        TextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(80.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        var password by remember { mutableStateOf("Enter password") }
        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(80.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Log in")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Log in as guest")
        }
        Divider(color = Color.Black, thickness = 1.dp, modifier = Modifier.padding(16.dp))
        Text(text = "If you don't have an account, Create one")
    }

}

@Preview
@Composable
fun LoginPagePreview() {
    LoginPage()
}