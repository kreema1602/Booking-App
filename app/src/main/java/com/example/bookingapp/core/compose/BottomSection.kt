package com.example.bookingapp.core.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.R
import com.example.bookingapp.core.ui.ThemedPreview
import com.example.bookingapp.core.ui.theme.OrangePrimary
import com.example.bookingapp.view_models.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun BottomSection(
    calendar: Boolean = false,
    price: String = "",
    buttonText: String = "",
    onClick: () -> Unit,
    dateRangeState: DateRangePickerState = rememberDateRangePickerState()
) {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.background(Color.Transparent)) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
                .align(Alignment.BottomEnd)
        ) {
            if (calendar) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(30.dp))
                        .border(1.dp, OrangePrimary, RoundedCornerShape(30.dp))
                        .background(OrangePrimary.copy(alpha = 0.08f))
                        .clickable(onClick = {
                            coroutineScope.launch {
                                bottomSheetState.show()
                            }
                        }),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
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
                    val from =
                        if (dateRangeState.selectedStartDateMillis != null) dateRangeState.selectedStartDateMillis?.let {
                            getFormattedDate(it)
                        } else "";
                    val to =
                        if (dateRangeState.selectedEndDateMillis != null) dateRangeState.selectedEndDateMillis?.let {
                            getFormattedDate(it)
                        } else "";
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (price.isNotEmpty()) {
                    Text(
                        text = "$price VND",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        ),
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
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(Color.Transparent)
                .fillMaxWidth()
        ) {
            ModalBottomSheetLayout(
                sheetState = bottomSheetState,
                sheetContent = {
                    MyDateRangePicker(dateRangeState, bottomSheetState, coroutineScope)
                },
                content = {},
                scrimColor = Color.Black.copy(alpha = 0.5f),
                sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
        }
    }
}
