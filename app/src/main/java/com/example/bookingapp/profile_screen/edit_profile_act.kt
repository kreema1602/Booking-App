package com.example.bookingapp.profile_screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.models.JoyhubAccount

class edit_profile_act : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val acc = intent.getSerializableExtra("ACCOUNT") as? JoyhubAccount
        if (acc != null) {
            Log.i("edit_profile_act", "onCreate: ${acc.get_email()}")
        }
        setContent {
            Edit_user_profile_form(acc!!) {
                finish()
            }
        }
    }
}

@Composable
fun Edit_user_profile_form(acc: JoyhubAccount, onBack: () -> Unit) {
    val acc_tmp = acc
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = androidx.compose.ui.Modifier.size(16.dp))
        Text(
            text = "Edit Profile",
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            modifier = androidx.compose.ui.Modifier.padding(16.dp)
        )
        TextField(
            value = acc_tmp.get_username(),
            onValueChange = { acc_tmp.set_username(it) },
            label = { Text("Username") },
            modifier = androidx.compose.ui.Modifier.padding(16.dp)
        )
        TextField(
            value = acc_tmp.get_email(),
            onValueChange = { acc_tmp.set_email(it) },
            label = { Text("Email") },
            modifier = androidx.compose.ui.Modifier.padding(16.dp)
        )
        TextField(
            value = acc_tmp.get_phone(),
            onValueChange = { acc_tmp.set_phone(it) },
            label = { Text("Phone") },
            modifier = androidx.compose.ui.Modifier.padding(16.dp)
        )
        //password
        TextField(
            value = acc_tmp.get_password(),
            onValueChange = { acc_tmp.set_password(it) },
            label = { Text("Password") },
            modifier = androidx.compose.ui.Modifier.padding(16.dp)
        )

        Spacer(modifier = androidx.compose.ui.Modifier.size(16.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Update")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Edit_user_profile_form(
        acc = JoyhubAccount(
            "Thieuquancute", "123", "thieuquan@gmail.com", "1234567890", 123
        )
    ) {}
}