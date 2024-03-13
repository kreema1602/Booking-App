package com.example.bookingapp.profile_screen

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
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

class Profile_main_screen : AppCompatActivity() {
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
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = "https://scontent.fsgn5-9.fna.fbcdn.net/v/t39.30808-6/426537568_3230198847282323_3777633320519734564_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeEy7wFkTZti3P-LHZ125WndGablwDNx4jsZpuXAM3HiO0e413eT3pk6j7bbjA0GAa6Tg9JserM0AyLhckuZVdBS&_nc_ohc=5U54vLYizzUAX-zxfQe&_nc_ht=scontent.fsgn5-9.fna&oh=00_AfAL_osxzv7xFOhHWCRpO5BgNVNdEY8ZH3ppM1Ro_FrE7A&oe=65F1FC0B",
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(3.dp))
            Column {
                Text(
                    text = acc.get_username(), modifier = Modifier.fillMaxWidth(), fontSize = 20.sp
                )
                Row {
                    Text(text = "JoyCoin")
                    Text(
                        text = acc.get_wallet().toString(),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = androidx.compose.ui.text.style.TextAlign.End
                    )
                }
            }
        }
    }
}

@Composable
fun Edit_user_profile(acc: JoyhubAccount) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 16.dp, 0.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
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
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
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
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 16.dp, 0.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        ) {
            Text(
                text = "Log out", modifier = Modifier.padding(16.dp), fontSize = 20.sp
            )
            Icon(Icons.Filled.ExitToApp, contentDescription = null)
        }
    }

}

@Preview
@Composable
fun Preview() {
    Profile_screen(JoyhubAccount("Thieuquancute", "123", "thieuquan@gmail.com", "1234567890", 123))
}
