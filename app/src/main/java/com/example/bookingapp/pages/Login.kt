package com.example.bookingapp.pages

import android.content.Context
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.bookingapp.R
import com.example.bookingapp.core.ui.theme.OrangePrimary
import com.example.bookingapp.navigation.GeneralLeafScreen
import com.example.bookingapp.navigation.RootScreen
import com.example.bookingapp.services.AccountService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun authorize(username: String, password: String): String {
    if (username == "hotel") return "moderator";
    else if (username == "customer") return "customer";
    return "guest";
}

@Composable
fun LoginPage(navController: NavController, role: MutableState<String>, isLoggedIn: MutableState<Boolean>, context: Context) {
    val mavenProFamily = FontFamily(
        Font(R.font.maven_pro_regular, FontWeight.Normal),
        Font(R.font.maven_pro_bold, FontWeight.Bold),
        Font(R.font.maven_pro_medium, FontWeight.Medium),
        Font(R.font.maven_pro_semibold, FontWeight.SemiBold)
    )
    Surface(color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 90.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = R.drawable.img_logo,
                contentDescription = null,
                modifier = Modifier.size(150.dp)
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


            var username by remember { mutableStateOf("") }
            TextField(
                value = username,
                onValueChange = { username = it },
                shape = RoundedCornerShape(80.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.White
                ),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .border(
                        1.dp, Color.Gray, RoundedCornerShape(100)
                    ),
                placeholder = { Text(text = "Enter username") }
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

            var password by remember { mutableStateOf("") }
            TextField(
                value = password,
                onValueChange = { password = it },
                shape = RoundedCornerShape(80.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.White
                ),
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .border(
                        1.dp, Color.Gray, RoundedCornerShape(100)
                    ),
                placeholder = { Text(text = "Enter password") }
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
                onClick = { navController.navigate(GeneralLeafScreen.ForgotPassword.route) },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 50.dp, bottom = 20.dp),

                )

            Button(
                onClick = {
//                    authorize(username, password).let {
//                        role.value = it
//                        if (role.value != "guest") {
//                            isLoggedIn.value = true
//                            navController.navigate(
//                                when (role.value) {
//                                    "customer" -> RootScreen.Customer.route
//                                    "moderator" -> RootScreen.Moderator.route
//                                    else -> RootScreen.Login.route
//                                }
//                            )
//                        }
//                    }
                    CoroutineScope(Dispatchers.Main).launch {
                        val result = withContext(Dispatchers.IO) {
                            AccountService.login(username, password, context)
                        }
                        if (result) {
                            role.value = "customer"
                            isLoggedIn.value = true
                            navController.navigate(RootScreen.Customer.route)
                        }
                    }
                },
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
                Icon(
                    Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = OrangePrimary
                )
            }

            Divider(
                color = Color.Black, thickness = 1.dp, modifier = Modifier.padding(16.dp)
            )

            ClickableText(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    ) {
                        append("If you don't have an account, ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = OrangePrimary,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Create one")
                    }
                },
                onClick = {
                    navController.navigate(GeneralLeafScreen.SignUp.route)
                },
            )
        }
    }
}

//@Preview
//@Composable
//fun LoginPagePreview() {
//    LoginPage {}
//}