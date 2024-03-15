@file:Suppress("LocalVariableName")

package com.example.bookingapp.profile_screen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bookingapp.R
import com.example.bookingapp.models.JoyhubAccount
import com.example.bookingapp.ui.theme.OrangePrimary


val mavenProFontFamily = (Font(R.font.maven_pro_regular, FontWeight.Normal) to Font(
    R.font.maven_pro_bold, FontWeight.Bold
))

class ProfileMainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Profile_screen(
                JoyhubAccount(
                    "Thieuquancute", "123", "thieuquan@gmail.com", "1234567890", 123
                )
            )
        }
    }
}


@Composable
fun Profile_screen(acc: JoyhubAccount) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Profile",
            modifier = Modifier.padding(16.dp),
            fontSize = 30.sp,
            fontFamily = FontFamily(mavenProFontFamily.first, mavenProFontFamily.second),
            fontWeight = FontWeight.Bold
        )
        Name_tag(acc)
        Spacer(modifier = Modifier.padding(6.dp))
        ConfigCard(acc)
    }
}


@Composable
fun ConfigCard(acc: JoyhubAccount) {
    Card {
        Column {
            Edit_user_profile(acc)
            Recharge_joycoin(acc)
            Recently_history(acc)
            Favorite_list(acc)
            Log_out(acc)
        }
    }
}

@Composable
fun Name_tag(acc: JoyhubAccount) {
    Card(colors = CardDefaults.cardColors(containerColor = OrangePrimary)) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            AsyncImage(
                model = R.drawable.ava,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Bottom) {
                Text(
                    text = acc.get_username(),
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 28.sp,
                    fontFamily = FontFamily(
                        mavenProFontFamily.first, mavenProFontFamily.second
                    ),
                    fontWeight = FontWeight.Bold,
                    color = androidx.compose.ui.graphics.Color.White
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "JoyCoin", fontFamily = FontFamily(
                            mavenProFontFamily.first, mavenProFontFamily.second
                        ), fontSize = 16.sp, color = androidx.compose.ui.graphics.Color.White
                    )
                    Text(
                        text = acc.get_wallet().toString(),
                        textAlign = androidx.compose.ui.text.style.TextAlign.End,
                        fontFamily = FontFamily(
                            mavenProFontFamily.first, mavenProFontFamily.second
                        ),
                        fontSize = 16.sp,
                        color = androidx.compose.ui.graphics.Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun Edit_user_profile(acc: JoyhubAccount) {
    Log.i("Profile_main_screen", "Edit_user_profile: ${acc.get_email()}")
    val context = LocalContext.current
    val editLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val edited_acc = data?.getSerializableExtra("account") as JoyhubAccount
            acc.set_username(edited_acc.get_username())
            acc.set_email(edited_acc.get_email())
            acc.set_phone(edited_acc.get_phone())
        }
    }
    Card(modifier = Modifier.clickable {
        val bundle = Bundle()
        val intent = android.content.Intent(context, edit_profile_act::class.java)
        bundle.putSerializable("ACCOUNT", acc)
        Log.i("Profile_main_screen", "Edit_user_profile: ${acc.get_email()}")
        intent.putExtras(bundle)
        editLauncher.launch(intent)
    }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 16.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Edit user profile", modifier = Modifier.padding(16.dp), fontSize = 20.sp
            )
            Icon(
                Icons.Rounded.Edit,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun Recharge_joycoin(acc: JoyhubAccount) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 16.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Recharge JoyCoin", modifier = Modifier.padding(16.dp), fontSize = 20.sp
            )
            Icon(
                Icons.Rounded.Add,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun Recently_history(acc: JoyhubAccount) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 16.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Recently history", modifier = Modifier.padding(16.dp), fontSize = 20.sp
            )
            Icon(
                Icons.Filled.Info,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun Favorite_list(acc: JoyhubAccount) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 16.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Favorite list", modifier = Modifier.padding(16.dp), fontSize = 20.sp
            )
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun Log_out(acc: JoyhubAccount) {
    Card {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 16.dp, 0.dp),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically,
        ) {
            Text(text = "Log out", modifier = Modifier.padding(16.dp), fontSize = 20.sp)
            Icon(Icons.Filled.ExitToApp, contentDescription = null)
        }
    }

}

@Preview
@Composable
fun Preview() {
    Profile_screen(JoyhubAccount("Thieuquancute", "123", "thieuquan@gmail.com", "1234567890", 123))
}
