package com.example.bookingapp.pages

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.bookingapp.R
import com.example.bookingapp.core.ui.theme.OrangePrimary
import com.example.bookingapp.navigation.CustomerLeafScreen
import com.example.bookingapp.navigation.GeneralLeafScreen
import com.example.bookingapp.navigation.ModeratorLeafScreen
import com.example.bookingapp.view_models.AuthViewModel
import org.koin.androidx.compose.koinViewModel
import com.example.bookingapp.core.ui.mavenProFontFamily as mavenProFamily

data class LoginTextFieldParams(
    val label: String,
    val value: String,
    val onValueChange: (String) -> Unit,
    val placeholder: String
)

data class PasswordTextFieldParams (
    val label: String,
    val value: String,
    val onValueChange: (String) -> Unit,
    val performLogin: () -> Unit,
    val placeholder: String
)
@Composable
fun LoginTextField(params: LoginTextFieldParams, modifier: Modifier = Modifier) {
    val (label, value, onValueChange, placeholder) = params
    val focusManager = LocalFocusManager.current

    Text(
        text = label,
        modifier = modifier
            .padding(start = 50.dp, bottom = 3.dp),
        fontFamily = mavenProFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

    TextField(
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(80.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.White
        ),
        singleLine = true,
        modifier = Modifier
            .padding(bottom = 20.dp)
            .border(
                1.dp, Color.Gray, RoundedCornerShape(100)
            )
            .onKeyEvent { keyEvent ->
                if (keyEvent.key == Key.Tab) {
                    focusManager.clearFocus()
                    focusManager.moveFocus(FocusDirection.Down)
                    true
                } else {
                    false
                }
            },
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.clearFocus()
                focusManager.moveFocus(FocusDirection.Next)
            }
        ),
    )
}

@Composable
fun PasswordTextField(params: PasswordTextFieldParams, modifier: Modifier = Modifier) {
    val (label, value, onValueChange, performLogin, placeholder) = params
    var visible by remember {
        mutableStateOf(false)
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Text(
        text = label,
        modifier = modifier
            .padding(start = 50.dp, bottom = 3.dp),
        fontFamily = mavenProFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

    TextField(
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(80.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.White
        ),
        singleLine = true,
        modifier = Modifier
            .padding(bottom = 10.dp)
            .border(
                1.dp, Color.Gray, RoundedCornerShape(100)
            )
            .onKeyEvent { keyEvent ->
                if (keyEvent.key == Key.Enter) {
                    keyboardController?.hide()
                    performLogin()
                    true
                } else {
                    false
                }
            },
        placeholder = { Text(text = placeholder) },
        visualTransformation = if (!visible)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        trailingIcon = {
            val icon =
                if (visible) Icons.Default.Visibility else Icons.Default.VisibilityOff
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = OrangePrimary,
                modifier = Modifier
                    .padding(end = 20.dp)
                    .clickable {
                        visible = !visible
                    }
            )
        },
        // perform login when user press new line on keyboard
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                performLogin()
            }
        ),
    )
}

@Composable
fun LoginPage(
    navController: NavController,
    authViewModel: AuthViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val isAuthenticated by authViewModel.isAuthenticated.collectAsState()
    val loginStatus by authViewModel.loginStatus.collectAsState()
    val account by authViewModel.account.collectAsState()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(loginStatus) {
        if (loginStatus.isNotEmpty()) {
            Toast.makeText(context, loginStatus, Toast.LENGTH_SHORT).show()
            if (isAuthenticated && account != null) {
                when (account!!.role) {
                    "customer" -> {
                        navController.navigate(CustomerLeafScreen.Home.route)
                    }

                    "moderator" -> {
                        navController.navigate(ModeratorLeafScreen.Home.route)
                    }
                }
            }
        }
    }

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


            LoginTextField(
                LoginTextFieldParams(
                    label = "Username",
                    value = username,
                    onValueChange = { username = it },
                    placeholder = "Enter username"
                ),
                modifier = Modifier.align(Alignment.Start)
            )

            PasswordTextField(
                PasswordTextFieldParams(
                    label = "Password",
                    value = password,
                    onValueChange = { password = it },
                    performLogin = {
                        Log.d("LoginPage", "Login: $username, $password")
                        authViewModel.login(username, password)
                    },
                    placeholder = "Enter password"
                ),
                modifier = Modifier.align(Alignment.Start)
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
                    authViewModel.login(username, password)
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
                onClick = {
//                    role.value = "guest"
//                    isLoggedIn.value = true
//                    navController.navigate(RootScreen.Customer.route)
                },
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