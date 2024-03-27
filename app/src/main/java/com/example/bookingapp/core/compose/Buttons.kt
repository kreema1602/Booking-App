package com.example.bookingapp.core.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.R
import com.example.bookingapp.core.ui.ThemedPreview
import com.example.bookingapp.core.ui.theme.OrangePrimary

@Composable
fun FilledButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) = Button(
    modifier = modifier,
    shape = MaterialTheme.shapes.small,
    onClick = onClick
) {
    Text(text = text)
}

@Composable
fun TonalButton(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
    backgroundColor: Color = Color.White,
    contentColor: Color = OrangePrimary,
    borderStroke: BorderStroke = BorderStroke(1.dp, OrangePrimary),
) = FilledTonalButton(
    modifier = modifier,
    contentPadding = contentPadding,
    shape = MaterialTheme.shapes.small,
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(
        containerColor = backgroundColor,
        contentColor = contentColor
    ),
    border = borderStroke
) {
    content()
}

@Composable
fun FilledNetworkButton(
    modifier: Modifier = Modifier,
    text: String,
    loading: Boolean,
    loadingText: String = "Please wait...",
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    color = Color.White,
                    strokeWidth = 2.5.dp
                )
                Spacer(modifier = Modifier.widthIn(min = 8.dp))
                Text(text = loadingText)
            } else {
                Text(text = text)
            }
        }
    }
}

@Composable
fun SimpleButton(text: String, onClick: () -> Unit, color: Color = OrangePrimary) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(100))
            .background(OrangePrimary)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(vertical = 14.dp, horizontal = 0.dp),
        )
    }
}

@Composable
fun FilledClipButton(
    text: String,
    onClick: () -> Unit,
    color: Color = OrangePrimary
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(100))
            .background(color)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFFFFF),
            modifier = Modifier
                .padding(vertical = 14.dp, horizontal = 0.dp),
        )
    }
}

@Preview
@Composable
private fun PreviewButtons() {
    ThemedPreview {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.horizontal_screen_padding)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilledButton(text = "Filled Button") {

            }
            TonalButton(onClick = { }, content = {
                Text(text = "Filled Tonal Button")
            })
            TonalButton(
                onClick = { },
                modifier = Modifier.widthIn(min = 32.dp),
                contentPadding = PaddingValues(horizontal = 8.dp),
                content = {
                    Text(text = "Filled Tonal Button")
                },
            )
            FilledNetworkButton(
                modifier = Modifier.width(200.dp),
                text = "Login", loading = true,
            ) {

            }
            FilledClipButton(text = "Register" ,onClick = { /*TODO*/ })
        }
    }
}