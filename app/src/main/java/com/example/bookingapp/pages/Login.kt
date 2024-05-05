package com.example.bookingapp.pages

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.bookingapp.MainActivity
import com.example.bookingapp.R
import com.example.bookingapp.core.ui.theme.OrangePrimary
import com.example.bookingapp.core.compose.BiometricAuthenticator
import com.example.bookingapp.navigation.GeneralLeafScreen
import com.example.bookingapp.view_models.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LoginPage(
    navController: NavController
) {
    val mavenProFamily = FontFamily(
        Font(R.font.maven_pro_regular, FontWeight.Normal),
        Font(R.font.maven_pro_bold, FontWeight.Bold),
        Font(R.font.maven_pro_medium, FontWeight.Medium),
        Font(R.font.maven_pro_semibold, FontWeight.SemiBold)
    )
    val context = MainActivity.context
    val activity = LocalContext.current as FragmentActivity
    var visible by remember {
        mutableStateOf(false)
    }
    val biometricAuthenticator = BiometricAuthenticator()

    val scope = rememberCoroutineScope()


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

            val focusManager = LocalFocusManager.current
            var username by remember { mutableStateOf("") }
            TextField(
                value = username,
                onValueChange = {
                    username = it
                },
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
                placeholder = { Text(text = "Enter username") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.clearFocus()
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                ),
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
            val keyboardController = LocalSoftwareKeyboardController.current
            TextField(
                value = password,
                onValueChange = { password = it },
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
                            performLogin(context, username, password, scope)
                            true
                        } else {
                            false
                        }
                    },
                placeholder = { Text(text = "Enter password") },
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
                        performLogin(context, username, password, scope)
                    }
                ),
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 45.dp, end = 45.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        performLogin(context, username, password, scope)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .weight(0.7f)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Log in",
                        fontFamily = mavenProFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                IconButton(onClick = {
                    biometricAuthenticator.promptBiometricAuth(
                        title = "Login",
                        subTitle = "Use your fingerprint",
                        negativeButtonText = "Cancel",
                        fragmentActivity = activity,
                        onSuccess = {
                            val credentials = MainViewModel.authViewModel.getCredentials()
                            if (credentials.first.isEmpty() || credentials.second.isEmpty()) {
                                scope.launch {
                                Toast.makeText(context, "Please login by your username and password first", Toast.LENGTH_SHORT)
                                    .show()
                                }
                                return@promptBiometricAuth
                            } else {
                                performLogin(context, credentials.first, credentials.second, scope)
                            }
                        },
                        onError = { _, errorString ->
                            scope.launch {
                                Toast.makeText(context, errorString.toString(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                        },
                        onFailed = {
                            scope.launch {
                                Toast.makeText(context, "Verification error", Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_fingerprint),
                        contentDescription = null,
                        tint = OrangePrimary,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }

            TextButton(
                onClick = {},
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

fun performLogin(
    context: Context,
    username: String,
    password: String,
    scope: CoroutineScope,
    isBio: Boolean = false
) {
    scope.launch {
        try {
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    context,
                    "Please fill username and password",
                    Toast.LENGTH_SHORT
                ).show()
                return@launch
            }
            val result = withContext(Dispatchers.IO) {
                MainViewModel.authViewModel.login(username, password)
            }

            if (result) {
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(
                    context,
                    "Invalid username or password!",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        } catch (e: Exception) {
            Log.e("Login", "Failed to login ${e.message}")
            Toast.makeText(
                context,
                "Failed to login ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

@Preview
@Composable
fun PreviewLoginPage() {
    // create a mock nav controller
    val navController = NavController(MainActivity.context)
    LoginPage(navController)
}
