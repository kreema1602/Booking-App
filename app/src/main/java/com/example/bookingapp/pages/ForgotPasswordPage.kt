package com.example.bookingapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookingapp.R
import com.example.bookingapp.core.compose.FilledClipButton
import com.example.bookingapp.core.compose.TopAppBar
import com.example.bookingapp.core.ui.theme.OrangePrimary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
            ForgotPasswordContent(navController = navController)
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
fun ForgotPasswordContent(
    navController: NavController
) {
    val usernameInput = remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }

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
                    backgroundColor = Color(0xFFf9f9f9),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color(0xFFff6400),
                    textColor = Color(0xFF000000),
                ),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(100))
                .background(OrangePrimary)
                .clickable {
                    showDialog.value = true
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Send code",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .padding(vertical = 14.dp, horizontal = 0.dp),
            )
        }

        if (showDialog.value) {
            SendCode(
                username = usernameInput.value,
                navController = navController,
                onDismissRequest = { showDialog.value = false }
            )
        }
    }
}

@Composable
fun SendCode(
    username: String,
    navController: NavController,
    onDismissRequest: () -> Unit
){
    ShowOTPDialog(navController = navController, onDismissRequest = { onDismissRequest() }, onConfirmation = { /*TODO*/ })
}

@Composable
fun ShowOTPDialog(
    navController: NavController,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
){
    val coroutineScope = rememberCoroutineScope()
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier.size(360.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(
                    text = "Forgot Password?",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(
                    text = "Enter the recovery code we just sent on your email address",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF888888),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 0.dp),
                )
                // OTP Text Field
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 24.dp, start = 0.dp, end = 0.dp)
                ) {
                    OTPTextField(length = 6, coroutineScope = coroutineScope)
                }

                // Verify Button
                FilledClipButton(
                    text = "Verify",
                    onClick = {
                        navController.navigate("new_password")
                    }
                )

                // Text for Resend Code
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, bottom = 0.dp, start = 0.dp, end = 0.dp),
//                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Didn’t receive the code?",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                    )
                    Text(
                        text = " Resend",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = OrangePrimary,
                        modifier = Modifier.clickable {
                            resendCode()
                        }
                    )
                }

                // Text for Back to Username
                Row(
                    modifier = Modifier
                        .padding(top = 18.dp, bottom = 0.dp, start = 0.dp, end = 0.dp)
                        .clickable { onDismissRequest() },
                ) {
                    Icon (
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Back to Username",
                        tint = OrangePrimary,
                        modifier = Modifier
                            .size(18.dp)
                    )
                    Text(
                        text = "Cancel",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = OrangePrimary,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun OTPTextField(
    length: Int,
    coroutineScope: CoroutineScope
){
    val requesterList = remember { List(length) { FocusRequester() } }
    val otp = remember {
        List(length) { mutableStateOf(TextFieldValue(text = "", selection = TextRange(0))) }
    }


    val focusManager = LocalFocusManager.current
    val keyBoardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(top = 16.dp),
    ) {
        for(i in otp.indices){
            OTPInputView(
                value = otp[i].value,
                focusRequester = requesterList[i],
                onValueChange = {newValue ->
                    if(otp[i].value.text != "") {
                        if (newValue.text == "") {
                            otp[i].value = TextFieldValue(
                                text = "",
                                selection = TextRange(0)
                            )
                        }
                        return@OTPInputView
                    }

                    otp[i].value = TextFieldValue(
                        text = newValue.text,
                        selection = TextRange(newValue.text.length)
                    )

                    nextFocus(otp = otp, focusRequesterList = requesterList, coroutineScope = coroutineScope)
                },
                onBackspacePressed = {
                    if(otp[i].value.text == ""){
                        previousFocus(otp = otp, focusRequesterList = requesterList, coroutineScope = coroutineScope)
                    }
                }
            )
        }
    }
    
    LaunchedEffect(key1 = null, block = {
        delay(300)
        requesterList[0].requestFocus()
    })


}

@Composable
fun OTPInputView(
    value: TextFieldValue,
    focusRequester: FocusRequester,
    onValueChange: (TextFieldValue) -> Unit,
    onBackspacePressed: () -> Unit,
) {
    BasicTextField(
        readOnly = false,
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .padding(end = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFC3C9D1), shape = RoundedCornerShape(8.dp))
            .wrapContentSize()
            .onKeyEvent { event ->
                if (event.key == Key.Backspace && value.text.isEmpty()) {
                    onBackspacePressed()
                    return@onKeyEvent true
                }
                return@onKeyEvent false
            }
            .focusRequester(focusRequester),
        maxLines = 1,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .size(40.dp, 48.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                innerTextField()
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(
            onDone = null
        ),
        cursorBrush = SolidColor(OrangePrimary),
        textStyle = TextStyle(
            color = OrangePrimary,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        ),
    )
}

fun nextFocus(
    otp: List<MutableState<TextFieldValue>>,
    focusRequesterList: List<FocusRequester>,
    coroutineScope: CoroutineScope
) {
    coroutineScope.launch {
        for (i in otp.indices) {
            if (otp[i].value.text != "") continue
            if(i < otp.size){
                delay(100)
                focusRequesterList[i].requestFocus()
                break
            }
        }
    }
}

fun previousFocus(
    otp: List<MutableState<TextFieldValue>>,
    focusRequesterList: List<FocusRequester>,
    coroutineScope: CoroutineScope
) {
    coroutineScope.launch {
        for (i in otp.indices) {
            if (otp[i].value.text != "") continue
            if(i > 0){
                delay(100)
                otp[i-1].value = TextFieldValue(
                    text = "",
                    selection = TextRange(0)
                )
                focusRequesterList[i - 1].requestFocus()
                break
            }
        }
    }
}

fun resendCode(){
    // Call api resend code
}
@Preview
@Composable
fun ForgotPasswordPagePreview() {
//    ForgotPasswordPage(navController = rememberNavController())
    ShowOTPDialog(navController = rememberNavController(), onDismissRequest = { /*TODO*/ }) {
        
    }
}
