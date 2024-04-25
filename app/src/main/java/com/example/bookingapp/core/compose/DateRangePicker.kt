package com.example.bookingapp.core.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.core.ui.theme.OrangePrimary
import kotlinx.coroutines.CoroutineScope
import java.text.SimpleDateFormat
import java.util.Calendar

@SuppressLint("SimpleDateFormat")
fun getFormattedDate(timeInMillis: Long): String {
    val calender = Calendar.getInstance()
    calender.timeInMillis = timeInMillis
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    return dateFormat.format(calender.timeInMillis)
}

fun dateValidator(): (Long) -> Boolean {
    return { timeInMillis ->
        val endCalenderDate = Calendar.getInstance()
        endCalenderDate.timeInMillis = timeInMillis
        endCalenderDate.set(Calendar.DATE, Calendar.DATE + 20)
        timeInMillis > Calendar.getInstance().timeInMillis && timeInMillis < endCalenderDate.timeInMillis
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MyDateRangePicker(state: DateRangePickerState, bottomSheetState: ModalBottomSheetState, coroutineScope: CoroutineScope) {

    state.setSelection(
        Calendar.getInstance().apply {
            add(Calendar.DATE, 1)
        }.timeInMillis,
        Calendar.getInstance().apply {
            add(Calendar.DATE, 2)
        }.timeInMillis
    )

    DateRangePicker(
        state,
        modifier = Modifier,
        dateFormatter = DatePickerFormatter("yy MM dd", "yy MM dd", "yy MM dd"),
        dateValidator = dateValidator(),
        title = {
            Text(
                text = "Select date range for your booking", modifier = Modifier
                    .padding(16.dp),
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp, color = OrangePrimary, fontWeight = FontWeight(700))
            )
        },
        headline = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Box(Modifier.weight(1f)) {
                    (if (state.selectedStartDateMillis != null) state.selectedStartDateMillis?.let {
                        getFormattedDate(
                            it
                        )
                    } else "Start Date")?.let { Text(text = it, fontSize = 18.sp) }
                }
                Box(Modifier.weight(1f)) {
                    (if (state.selectedEndDateMillis != null) state.selectedEndDateMillis?.let {
                        getFormattedDate(
                            it
                        )
                    } else "End Date")?.let { Text(text = it, fontSize = 18.sp) }
                }
                Box(Modifier.weight(0.2f)) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = "Okk",
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .clickable(
                                onClick = {
                                    coroutineScope.launch {
                                        bottomSheetState.hide()
                                    }
                                }
                            )
                            .clip(RoundedCornerShape(50))
                            .size(24.dp)
                    )
                }

            }
        },
        showModeToggle = true,
        colors = DatePickerDefaults.colors(
            containerColor = Color.Blue,
            titleContentColor = OrangePrimary,
            headlineContentColor = Color.Black,
            weekdayContentColor = Color.Black,
            subheadContentColor = Color.Black,
            yearContentColor = Color.Green,
            currentYearContentColor = Color.Red,
            selectedYearContainerColor = Color.Red,
            disabledDayContentColor = Color.Gray,
            todayDateBorderColor = OrangePrimary,
            dayInSelectionRangeContainerColor = Color.LightGray,
            dayInSelectionRangeContentColor = OrangePrimary,
            selectedDayContainerColor = OrangePrimary
        )
    )
}
