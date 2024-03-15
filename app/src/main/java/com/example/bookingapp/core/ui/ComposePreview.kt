package com.example.bookingapp.core.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.bookingapp.core.ui.theme.BookingAppTheme

@Composable
internal fun ThemedPreview(
    darkTheme: Boolean = false,
    uiMode: UiMode = UiMode.PhonePortrait,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalUiMode provides uiMode){
        BookingAppTheme(darkTheme = darkTheme) {
            Surface(color = MaterialTheme.colorScheme.background) {
                content()
            }
        }
    }
}