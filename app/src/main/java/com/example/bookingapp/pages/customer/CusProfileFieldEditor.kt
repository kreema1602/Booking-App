package com.example.bookingapp.pages.customer

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookingapp.MainActivity
import com.example.bookingapp.core.ui.theme.OrangePrimary
import com.example.bookingapp.pages.hotelier.mavenProFamily
import com.example.bookingapp.models.Account
import com.example.bookingapp.models.requests.UpdateAccountRequest
import com.example.bookingapp.navigation.CustomerLeafScreen
import com.example.bookingapp.view_models.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun CusProfileFieldEditor(accountId: String, navController: NavController) {
    var account by remember { mutableStateOf (Account()) }
    LaunchedEffect(Unit) {
        try {
            account = MainViewModel.accountViewModel.getProfile("customer", accountId)
        } catch (e: Exception) {
            Log.e("CusProfileFieldEditor", "getProfile: ${e.message}")
        }
    }

    val fullname = remember { mutableStateOf("") }
    fullname.value = account.fullname

    val email = remember { mutableStateOf("") }
    email.value = account.email

    val phone = remember { mutableStateOf("") }
    phone.value = account.phone

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "Edit Profile",
            fontSize = 24.sp,
            fontFamily = mavenProFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        EditProfileField(
            label = "Full Name",
            fieldValue = fullname
        )
        EditProfileField(
            label = "Email",
            fieldValue = email
        )
        EditProfileField(
            label = "Phone",
            fieldValue = phone
        )
        Button(
            onClick = {
                CoroutineScope(Dispatchers.Main).launch {
                    updateProfile(
                        accountId,
                        fullname.value,
                        email.value,
                        phone.value,
                        navController
                    )
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, bottom = 10.dp)
                .height(50.dp)
        ) {
            Text(
                text = "Save",
                fontFamily = mavenProFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun EditProfileField(
    label: String,
    fieldValue: MutableState<String>
) {
    TextField(
        value = fieldValue.value,
        onValueChange = { fieldValue.value = it },
        label = { Text(label) },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    )
}

suspend fun updateProfile (
    accountId: String,
    fullname: String,
    email: String,
    phone: String,
    navController: NavController
) {
    val updateAccountRequest = UpdateAccountRequest().apply {
        this.fullname = fullname
        this.email = email
        this.phone = phone
    }
    val result = withContext(Dispatchers.IO) {
        try {
            MainViewModel.accountViewModel.updateProfile("customer" ,accountId, updateAccountRequest)
        } catch (e: Exception) {
            Log.e("CusProfileFieldEditor", "updateProfile: ${e.message}")
            false
        }
    }

    if (result) {
        Toast.makeText(MainActivity.context, "Update successfully", Toast.LENGTH_SHORT).show()
        navController.navigate(CustomerLeafScreen.Profile.route)
    } else {
        Log.d("CusProfileFieldEditor", "updateProfile: Failed")
    }
}