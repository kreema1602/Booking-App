package com.example.bookingapp.core.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.example.bookingapp.R
import com.example.bookingapp.core.ui.ThemedPreview

@Composable
fun EditText(
    modifier: Modifier = Modifier,
    hint: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true,
    fontSize: TextUnit = MaterialTheme.typography.bodyMedium.fontSize
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = text,
        placeholder = { Text(text = hint) },
        onValueChange = { text = it },
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = modifier.fillMaxWidth(),
        textStyle = TextStyle(fontSize = fontSize)
    )
}

@Composable
fun SearchBox(
    modifier: Modifier = Modifier
) {
    val text = rememberSaveable { mutableStateOf("") }
    TextField(
        value = text.value,
        placeholder = { Text(text = stringResource(id = R.string.search_box_placeholder)) },
        onValueChange = { newValue: String -> text.value = newValue },
        singleLine = true,
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = modifier.fillMaxWidth(),
        leadingIcon = { SearchIcon() }
    )
}

@Preview
@Composable
private fun PreviewSearchBox() {
    ThemedPreview {
        Column {
            SearchBox(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.onboarding_padding))
            )
            EditText(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.onboarding_padding)),
                hint = stringResource(id = R.string.hint_email),
            )
        }
    }
}