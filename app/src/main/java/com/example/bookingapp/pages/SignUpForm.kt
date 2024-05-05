package com.example.bookingapp.pages

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
import com.example.bookingapp.MainActivity
import com.example.bookingapp.R
import com.example.bookingapp.core.compose.FilledClipButton
import com.example.bookingapp.core.compose.TopAppBar
import com.example.bookingapp.view_models.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
@Composable
fun Title(role: String) {
    val description = when (role) {
        "customer" -> "Enter your personal information"
        "moderator" -> "Enter hotel's information"
        else -> "Enter your details below"
    }
    Column(
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
fun SignUpForm(navController: NavController, role: String) {

    val fieldsMap = mapOf(
        "username" to remember { mutableStateOf("") },
        "email" to remember { mutableStateOf("") },
        "password" to remember { mutableStateOf("") },
        "phone" to remember { mutableStateOf("") },
        "fullName" to remember { mutableStateOf("") },
        "confirmPassword" to remember { mutableStateOf("") },
        "hotelName" to remember { mutableStateOf("") },
        "hotelAddress" to remember { mutableStateOf("") },
        "description" to remember { mutableStateOf("") }
    )

    val acceptTermState = remember { mutableStateOf(false) }

    Surface(
        color = Color(0xFFF9F9F9),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopAppBar(
                title = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                onClick = { navController.popBackStack() },
            )
            Title(role = role)
            RegisterFormContent(
                role = role,
                fieldsMap = fieldsMap,
                acceptTermState = acceptTermState,
                onRegisterClicked = {
                    CoroutineScope(Dispatchers.Main).launch {
                        performValidation(
                            navController,
                            fieldsMap,
                            acceptTermState.value,
                            role
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun RegisterFormContent(
    role: String,
    fieldsMap: Map<String, MutableState<String>>,
    acceptTermState: MutableState<Boolean>,
    onRegisterClicked: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
    ) {
        val formFields = getFormFields(role)
        items(formFields.size) { index ->

            fieldsMap[getFieldMap(formFields[index])]?.let {
                FormField(
                    label = formFields[index],
                    fieldValue = it
                )
            }
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                CheckBoxAcceptTerm(acceptTermState)
                FilledClipButton(text = "Register", onClick = { onRegisterClicked() })
            }
        }
    }
}

@Composable
fun FormField(label: String, fieldValue: MutableState<String>) {
    var isPassword by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    if (label.lowercase().contains("password")) {
        isPassword = true
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 20.dp)
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
            visualTransformation = if (isPassword && !passwordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                if (!isPassword) return@TextField
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            }
        )
    }
}

@Composable
fun CheckBoxAcceptTerm(acceptTermState: MutableState<Boolean>) {
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

val fields = listOf(
    "Username",
    "Full Name",
    "Email",
    "Phone number",
    "Password",
    "Confirm Password",
    "Hotel's name",
    "Hotel's address",
    "Description"
)

fun getFormFields(role: String): List<String> {
    return when (role.lowercase()) {
        "customer" -> listOf(
            fields[0],
            fields[1],
            fields[2],
            fields[3],
            fields[4],
            fields[5]
        )
        "moderator" -> listOf(
            fields[0],
            fields[1],
            fields[2],
            fields[3],
            fields[4],
            fields[6],
            fields[7],
            fields[8]
        )

        else -> listOf(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5])
    }
}

fun getFieldMap(field: String): String {
    return when (field) {
        fields[0] -> "username"
        fields[1] -> "fullName"
        fields[2] -> "email"
        fields[3] -> "phone"
        fields[4] -> "password"
        fields[5] -> "confirmPassword"
        fields[6] -> "hotelName"
        fields[7] -> "hotelAddress"
        fields[8] -> "description"
        else -> "not found"
    }
}

suspend fun performValidation(
    navController: NavController,
    fieldsMap: Map<String, MutableState<String>>,
    acceptTermState: Boolean,
    role: String
) {
    Log.i("SignUpForm", "performValidation")
    val fieldsCheckMap = mutableMapOf<String, String>()
    val context = MainActivity.context

    // Initialize fieldsCheckMap based on the role
    when (role.lowercase()) {
        "customer" -> {
            fieldsCheckMap["username"] = fieldsMap["username"]?.value ?: ""
            fieldsCheckMap["email"] = fieldsMap["email"]?.value ?: ""
            fieldsCheckMap["password"] = fieldsMap["password"]?.value ?: ""
            fieldsCheckMap["confirmPassword"] = fieldsMap["confirmPassword"]?.value ?: ""
            fieldsCheckMap["phone"] = fieldsMap["phone"]?.value ?: ""
            fieldsCheckMap["fullName"] = fieldsMap["fullName"]?.value ?: ""
        }

        "moderator" -> {
            fieldsCheckMap["username"] = fieldsMap["username"]?.value ?: ""
            fieldsCheckMap["email"] = fieldsMap["email"]?.value ?: ""
            fieldsCheckMap["password"] = fieldsMap["password"]?.value ?: ""
            fieldsCheckMap["confirmPassword"] = fieldsMap["confirmPassword"]?.value ?: ""
            fieldsCheckMap["phone"] = fieldsMap["phone"]?.value ?: ""
            fieldsCheckMap["fullName"] = fieldsMap["fullName"]?.value ?: ""
            fieldsCheckMap["hotelName"] = fieldsMap["hotelName"]?.value ?: ""
            fieldsCheckMap["hotelAddress"] = fieldsMap["hotelAddress"]?.value ?: ""
            fieldsCheckMap["description"] = fieldsMap["description"]?.value ?: ""
        }
    }

    for ((field, value) in fieldsCheckMap) {
        if (value.isEmpty()) {
            val formattedField = field.replaceFirstChar { it.uppercase() }
            val splitField = formattedField.replace(Regex("([a-z])([A-Z])"), "$1 $2")

            Toast.makeText(context, "$splitField is empty", Toast.LENGTH_SHORT).show()
            return
        }
    }

    // Check format of email
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[A-Za-z0-9.-]\$"
    if (!fieldsCheckMap["email"]?.matches(emailRegex.toRegex())!!) {
        Log.i("SignUpForm", "Invalid email format")
        Toast.makeText(context, "Invalid email format", Toast.LENGTH_SHORT).show()
        return
    }

    // Check format of phone number
    val phoneRegex = "^[0-9]{10,11}\$"
    if (!fieldsCheckMap["phone"]?.matches(phoneRegex.toRegex())!!) {
        Log.i("SignUpForm", "Invalid phone number format")
        Toast.makeText(context, "Invalid phone number format", Toast.LENGTH_SHORT).show()
        return
    }

    if (!fieldsCheckMap["password"].equals(fieldsCheckMap["confirmPassword"])) {
        Log.i("SignUpForm", "Passwords do not match")
        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
        return
    }

    if (!acceptTermState) {
        Toast.makeText(context, "Please accept the terms of service", Toast.LENGTH_SHORT).show()
        return
    }

    try {
        val result = withContext(Dispatchers.IO) {
            MainViewModel.authViewModel.register(fieldsCheckMap, role)
        }

        if (result) {
            Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
            navController.navigate("login")
        } else {
            Toast.makeText(context, "Registration failed", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        Toast.makeText(context, "Registration failed: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}


@Preview
@Composable
fun PreviewSignUpForm() {
    SignUpForm(navController = rememberNavController(), role = "moderator")
}