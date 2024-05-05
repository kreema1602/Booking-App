package com.example.bookingapp.core.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class MainContentParams @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class) constructor(
    val calendar: Boolean,
    val state: DateRangePickerState,
    val bottomSheetState: ModalBottomSheetState,
    val coroutineScope: CoroutineScope,
    val price: String,
    val buttonText: String,
    val onClick: () -> Unit
)

data class BottomModalParams @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class) constructor(
    val bottomSheetState: ModalBottomSheetState,
    val state: DateRangePickerState,
    val coroutineScope: CoroutineScope
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    params: MainContentParams,
    modifier: Modifier = Modifier
) {
    val (calendar, state, bottomSheetState, coroutineScope, price, buttonText, onClick) = params
    Column(
        modifier = modifier
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        if (calendar) {
            CalendarRow(state, bottomSheetState, coroutineScope)
        }

        PriceAndButtonRow(price, buttonText, onClick)
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CalendarRow(
    state: DateRangePickerState,
    bottomSheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .border(1.dp, OrangePrimary, RoundedCornerShape(30.dp))
            .background(OrangePrimary.copy(alpha = 0.08f))
            .clickable {
                coroutineScope.launch {
                    bottomSheetState.show()
                }
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CalendarIcon()
        CalendarText(state)
    }
}

@Composable
fun CalendarIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_calendar_month),
        contentDescription = null,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .size(30.dp),
        tint = OrangePrimary
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarText(state: DateRangePickerState) {
    val from = state.selectedStartDateMillis?.let { getFormattedDate(it) } ?: ""
    val to = state.selectedEndDateMillis?.let { getFormattedDate(it) } ?: ""
    Text(
        text = "$from - $to",
        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun PriceAndButtonRow(price: String, buttonText: String, onClick: () -> Unit) {
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
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }
        if (buttonText.isNotEmpty()) {
            SimpleButton(text = buttonText, onClick = onClick)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetModal(
    params: BottomModalParams,
    modifier: Modifier = Modifier
) {
    val (bottomSheetState, state, coroutineScope) = params
    Column(
        modifier = modifier
            .background(Color.Transparent)
            .fillMaxWidth()
    ) {
        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetContent = {
                MyDateRangePicker(state, bottomSheetState, coroutineScope)
            },
            content = {},
            scrimColor = Color.Black.copy(alpha = 0.5f),
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun BottomSection(
    calendar: Boolean = false,
    price: String = "",
    buttonText: String = "",
    onClick: () -> Unit
) {
    val state = rememberDateRangePickerState()
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.background(Color.Transparent)) {
        MainContent(MainContentParams( calendar, state, bottomSheetState, coroutineScope, price, buttonText, onClick ), modifier = Modifier.align(Alignment.BottomCenter))
        BottomSheetModal(BottomModalParams(bottomSheetState, state, coroutineScope), modifier = Modifier.align(Alignment.BottomEnd))
    }
}

@Preview
@Composable
fun BottomSectionPreview() {
    ThemedPreview {
        BottomSection(calendar = true, price = "400.000", buttonText = "Book", onClick = {})
//        SimpleButton(text = "Book", onClick = {})
    }
}
