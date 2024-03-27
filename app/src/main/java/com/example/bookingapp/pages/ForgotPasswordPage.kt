package com.example.bookingapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookingapp.R
import com.example.bookingapp.core.compose.FilledClipButton
import com.example.bookingapp.core.compose.TopAppBar

@Composable
fun ForgotPasswordPage(navController: NavController) {
    Surface(
        color = Color(0xFFF9F9F9),
    ) {
        Column( // Use Column with Modifier.fillMaxSize()  */
            modifier = Modifier.fillMaxSize()
        ) {
            TopAppBar (
                title = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                onClick = { navController.popBackStack() },
            )
            ForgotImage()
            ForgotPasswordContent()
        }
    }
}

@Composable
fun ForgotImage(){
    val image = painterResource(id = R.drawable.forgot1)
    Image(
        painter = image,
        contentDescription = "Forgot Password Image",
        modifier = Modifier
            .fillMaxWidth()
            .size(360.dp)
    )
}

@Composable
fun ForgotPasswordContent() {
    val usernameInput = remember { mutableStateOf("") }
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Forgot Password?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = "Don’t worry ! Enter the username, we will send recovery code to your email address.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF888888),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp, start = 0.dp, end = 0.dp),
        )

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 20.dp, start = 0.dp, end = 0.dp)
        ) {
            Text(
                text = "Username",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 0.dp),
            )
            TextField(
                value = usernameInput.value,
                onValueChange = { usernameInput.value = it },
                placeholder = { Text(text = "Username") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 0.dp)
                    .border(1.dp, Color(0xFFDEE7F5), shape = RoundedCornerShape(100)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFFf9f9f9), // Modify background color
                    focusedIndicatorColor = Color.Transparent, // Remove the focus indicator
                    unfocusedIndicatorColor = Color.Transparent, // Remove the unfocused indicator
                    cursorColor = Color(0xFFff6400), // Modify cursor color
                    textColor = Color(0xFF000000), // Modify text color
                ),
            )
        }
        FilledClipButton(text = "Send code", onClick = {  })
    }
}

@Preview
@Composable
fun ForgotPasswordPagePreview() {
    ForgotPasswordPage(navController = rememberNavController())
}