package com.example.bookingapp.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookingapp.core.compose.FilledClipButton
import com.example.bookingapp.core.compose.TopAppBar

@Composable
fun NewPasswordPage(navController: NavController) {
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
            TopTitle(title = "Create new password")
            SubmitForm()
        }
    }
}

@Composable
fun TopTitle(
    title: String,
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp, horizontal = 20.dp)
    ) {
        // Title text
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 0.dp),
        )
        // Description text
        Text(
            text = "Your new password must be unique from those previously used",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF888888),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, bottom = 15.dp, start = 0.dp, end = 0.dp),
        )
    }
}

@Composable
fun SubmitForm(

) {
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp, horizontal = 20.dp)
    ) {
        InputField(label = "Password", fieldValue = password)
        InputField(label = "Confirm Password", fieldValue = confirmPassword)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 0.dp),
        ) {
            FilledClipButton(
                text = "Reset Password",
                onClick = {  },
            )
        }

    }
}

@Composable
fun InputField(
    label: String,
    fieldValue: MutableState<String>
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 0.dp)
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 0.dp),
        )
        TextField(
            value = fieldValue.value,
            onValueChange = { fieldValue.value = it },
            placeholder = { Text(text = "Enter ${label.lowercase()}") },
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
            visualTransformation = if (!passwordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector  = image, description)
                }
            }
        )
    }
}

@Preview
@Composable
fun NewPasswordPagePreview() {
    NewPasswordPage(navController = rememberNavController())
}