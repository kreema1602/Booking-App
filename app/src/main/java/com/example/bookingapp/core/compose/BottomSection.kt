package com.example.bookingapp.core.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.R
import com.example.bookingapp.core.ui.ThemedPreview
import com.example.bookingapp.core.ui.theme.OrangePrimary

@Composable
fun BottomSection(from: String = "", to: String = "", price: String = "", buttonText: String = "", onClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
            top = 12.dp,
            bottom = 12.dp
        )
    ) {
        if (from.isNotEmpty() && to.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp))
                    .border(1.dp, OrangePrimary, RoundedCornerShape(30.dp))
                    .background(OrangePrimary.copy(alpha = 0.08f)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar_month),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(
                            top = 8.dp,
                            bottom = 8.dp,
                            end = 8.dp
                        )
                        .size(30.dp),
                    tint = OrangePrimary,
                )
                Text(
                    text = "$from - $to",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(
                        top = 8.dp,
                        bottom = 8.dp
                    )
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (price.isNotEmpty()) {
                Text(
                    text = "$price VND",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, fontSize = 22.sp),
                    modifier = Modifier.padding(
                        top = 8.dp,
                        bottom = 8.dp,
                        end = 16.dp
                    )
                )
            }
            if (buttonText.isNotEmpty()) {
                SimpleButton(text = buttonText, onClick = onClick)
            }
        }
    }
}

@Preview
@Composable
fun BottomSectionPreview() {
    ThemedPreview {
        BottomSection(from = "Thu, 4/6/2023", to = "Sat, 6/6/2023", price = "400.000", buttonText = "Book", onClick = {})
//        SimpleButton(text = "Book", onClick = {})
    }
}
