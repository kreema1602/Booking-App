package com.example.bookingapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.bookingapp.R
import com.example.bookingapp.core.compose.TopAppBar

@Composable
fun SignUpPage(navController: NavController) {
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
            Spacer(modifier = Modifier.weight(0.5f)) // Add Spacer to fill remaining space vertically
            SignUpOption(navController = navController) // Place SignUpOption at the bottom center
            Spacer(modifier = Modifier.weight(1f)) // Add Spacer to fill remaining space vertically
        }
    }
}

@Composable
fun SignUpOption(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(
            text = "You are",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(500),
        ) // Add title here
        Spacer(modifier = Modifier.height(40.dp)) // Add spacing between title and options
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OptionItem(text = "Customer", iconResId = R.drawable.icon_customer , des = "who want to book accommodation", onClick = {
                navController.navigate("sign_up_form/customer")
            })
            OptionItem(text = "Moderator", iconResId = R.drawable.icon_moderator, des = "who provide accommodation", onClick = {
                navController.navigate("sign_up_form/moderator")
            })
        }
    }
}

@Composable
fun OptionItem(text: String, iconResId: Int, des: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
            .size(132.dp, 183.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                fontSize = 20.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFFFF6400),
            )
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null, // Provide proper content description
                modifier = Modifier.size(96.dp)
            )
            Text(
                text = des,
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF888888),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
fun SignUpPagePreview() {
    val navController = rememberNavController()
    SignUpPage(navController = navController)
}