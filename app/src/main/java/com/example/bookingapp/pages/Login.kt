package com.example.bookingapp.pages

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bookingapp.R
import com.example.bookingapp.core.ui.theme.OrangePrimary

@Composable
fun LoginPage() {
    val mavenProFamily = FontFamily(
        Font(R.font.maven_pro_regular, FontWeight.Normal),
        Font(R.font.maven_pro_bold, FontWeight.Bold),
        Font(R.font.maven_pro_medium, FontWeight.Medium),
        Font(R.font.maven_pro_semibold, FontWeight.SemiBold)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = R.drawable.img_logo, contentDescription = null, modifier = Modifier.size(150.dp)
        )

        AsyncImage(
            model = R.drawable.logo,
            contentDescription = null,
            modifier = Modifier.size(180.dp, 60.dp)
        )

        Text(
            text = "where you can discover, book",
            fontFamily = mavenProFamily,
            fontWeight = FontWeight.Normal,
        )

        Text(
            text = "or host a room",
            fontFamily = mavenProFamily,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(bottom = 20.dp)
        )


        Text(
            text = "Username",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 50.dp, bottom = 3.dp),
            fontFamily = mavenProFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )


        var username by remember { mutableStateOf("Enter username") }
        TextField(
            value = username,
            onValueChange = { username = it },
            shape = RoundedCornerShape(80.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Gray
            ),
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Text(
            text = "Password",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 50.dp, bottom = 3.dp),
            fontFamily = mavenProFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )

        var password by remember { mutableStateOf("Enter password") }
        TextField(
            value = password,
            onValueChange = { password = it },
            shape = RoundedCornerShape(80.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Gray
            ),
            modifier = Modifier.padding(bottom = 10.dp)
        )


        ClickableText(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = OrangePrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Forgot password?")
                }
            },
            onClick = { Log.d("LoginPage", "Forgot password clicked") },
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 50.dp, bottom = 20.dp),

            )

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 45.dp, end = 45.dp, bottom = 10.dp)
                .height(50.dp)
        ) {
            Text(
                text = "Log in",
                fontFamily = mavenProFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        TextButton(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.textButtonColors(contentColor = OrangePrimary),
            modifier = Modifier.size(200.dp, 50.dp)
        ) {
            Text(
                text = "Log in as guest",
                fontFamily = mavenProFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Icon(Icons.Default.ArrowForward, contentDescription = null, tint = OrangePrimary)
        }

        Divider(
            color = Color.Black, thickness = 1.dp, modifier = Modifier.padding(16.dp)
        )

        Text(
            text = "If you don't have an account, Create one",
            fontFamily = mavenProFamily,
            fontWeight = FontWeight.Normal
        )
    }
}

@Preview
@Composable
fun LoginPagePreview() {
    LoginPage()
}