package com.example.bookingapp.pages

import android.widget.CheckBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookingapp.R

@Composable
fun SignUpForm(navController: NavController, role: String) {
    Surface(
        color = Color(0xFFF9F9F9),
    ) {
        Column( // Use Column with Modifier.fillMaxSize()  */
            modifier = Modifier.fillMaxSize()
        ) {
            TopBar(navController = navController) // Place TopBar at the top
            Title(role = role) // Place Title below TopBar
            RegisterForm(navController = navController, role = role)
        }
    }
}

@Composable
fun Title(role: String) {
    var description = when (role) {
        "customer" -> "Enter your personal information"
        "moderator" -> "Enter hotel's information"
        else -> "Enter your details below"
    }
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp, horizontal = 20.dp)
    ) {
        // Image clipped with rounded corners in a Box
        Box(
            modifier = Modifier
                .size(98.dp)
                .clip(RoundedCornerShape(100))
                .background(Color(0xFFF9EDE5))
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.contact_form),
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp),
                colorFilter = ColorFilter.tint(Color(0xFFff6400))
            )
        }
        // Title text
        Text(
            text = "${role.replaceFirstChar { it.uppercase() }} Registration",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 0.dp),
        )
        // Description text
        Text(
            text = description,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF888888),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, bottom = 16.dp, start = 0.dp, end = 0.dp),
        )
    }
}

@Composable
fun RegisterForm(navController: NavController, role: String) {
    val formFields = when (role.lowercase()) {
        "customer" -> listOf("Username", "Fullname", "Password", "Confirm Password")
        "moderator" -> listOf("Username", "Hotel's name", "Password", "Confirm Password", "Hotel's address", "Description")
        // Add more cases for other roles if needed
        else -> listOf("Username", "Fullname", "Password", "Confirm Password")
    }
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
    ) {
        items(formFields.size) { index ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 20.dp)
            ) {
                Text(
                    text = formFields[index],
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp, horizontal = 0.dp),
                )
                TextFieldComponent(formFields[index])
            }
        }
        item {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                CheckBoxAcceptTerm()
                ButtonComponent(navController = navController)
            }
        }

    }
}

@Composable
fun TextFieldComponent(label : String) {
    var textValue by remember { mutableStateOf("") }
    val isPassword = label == "Password" || label == "Confirm Password"

    TextField(
        value = textValue,
        onValueChange = { textValue = it },
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

    )
}

@Composable
fun CheckBoxAcceptTerm(){
    val acceptTermState = remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp, top = 0.dp, start = 0.dp, end = 0.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Checkbox(
            checked = acceptTermState.value,
            onCheckedChange = { isChecked -> acceptTermState.value = isChecked },
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFFff6400),
                uncheckedColor = Color(0xFFff6400),
                checkmarkColor = Color(0xFFFFFFFF),
            )
        )
        Text(
            text = "I accept the ",
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
        )
        Text(
            text = "Terms of Service",
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFFff6400),
        )
    }
}

@Composable
fun ButtonComponent(navController: NavController) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(100))
                .background(Color(0xFFff6400))
                .clickable {
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Register",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .padding(vertical = 14.dp, horizontal = 0.dp),
            )
        }
}

@Preview
@Composable
fun PreviewSignUpForm() {
    SignUpForm(navController = rememberNavController(), role = "customer")
}