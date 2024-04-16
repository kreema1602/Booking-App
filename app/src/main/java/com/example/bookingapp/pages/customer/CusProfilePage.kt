package com.example.bookingapp.pages.customer

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.bookingapp.R
import com.example.bookingapp.core.ui.mavenProFontFamily
import com.example.bookingapp.core.ui.theme.OrangePrimary
import com.example.bookingapp.mock_data.AccountData
import com.example.bookingapp.models.Account
import com.example.bookingapp.view_models.MainViewModel

@Composable
fun CusProfilePage(accId: Int, onClickEdit: (String) -> Unit, onClickLogout: () -> Unit, onClickFavorite: () -> Unit, onClickHistory: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        val acc = AccountData.sampleData.find { it._id == accId.toString() }!!
        Text(
            text = "Profile",
            fontSize = 30.sp,
            fontFamily = FontFamily(mavenProFontFamily.first, mavenProFontFamily.second),
            fontWeight = FontWeight.Bold,
        )
        NameTag(acc)
        Spacer(modifier = Modifier.padding(16.dp))
        ConfigCard(acc, onClickEdit, onClickLogout, onClickFavorite, onClickHistory)
    }
}


@Composable
fun ConfigCard(acc: Account, onClickEdit: (String) -> Unit, onClickLogout: () -> Unit, onClickFavorite: () -> Unit, onClickHistory: () -> Unit) {
    Card {
        Column {
            ProfileEditor(acc, onClickEdit)
            RechargeJoycoin(acc)
            RecentlyHistory(acc, onClickHistory)
            FavoriteList(acc, onClickFavorite)
            LogOut(onClickLogout)
        }
    }
}

@Composable
fun NameTag(acc: Account) {
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
                    text = acc.username,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 28.sp,
                    fontFamily = FontFamily(
                       mavenProFontFamily.first, mavenProFontFamily.second
                    ),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "JoyCoin", fontFamily = FontFamily(
                            mavenProFontFamily.first, mavenProFontFamily.second
                        ), fontSize = 16.sp, color = Color.White
                    )
                    Text(
                        text = acc.wallet.toString(),
                        textAlign = TextAlign.End,
                        fontFamily = FontFamily(
                            mavenProFontFamily.first, mavenProFontFamily.second
                        ),
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileEditor(acc: Account, onClickEdit: (String) -> Unit) {
    val context = LocalContext.current
    val editLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val editedAcc = data?.getSerializableExtra("ACCOUNT") as? Account
            acc.username = editedAcc!!.username
            acc.email = editedAcc.email
            acc.phone = editedAcc.phone
        }
    }
    Card(modifier = Modifier.clickable {
        onClickEdit(acc._id)
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
fun RechargeJoycoin(acc: Account) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecentlyHistory(acc: Account, onClickHistory: () -> Unit) {
    Card(
        onClick = {
            onClickHistory()
        }
    ) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteList(acc: Account, onClickFavorite: () -> Unit) {
    Card(
        onClick = {
            onClickFavorite()
        }
    ) {
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
fun LogOut(onClickLogout: () -> Unit) {
    Card( modifier = Modifier.clickable { MainViewModel.authViewModel.logout() }) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 16.dp, 0.dp),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
                Text(text = "Log out", modifier = Modifier.padding(16.dp), fontSize = 20.sp)
                Icon(Icons.Filled.ExitToApp, contentDescription = null)

        }
    }

}