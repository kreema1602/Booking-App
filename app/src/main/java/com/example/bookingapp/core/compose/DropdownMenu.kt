package com.example.bookingapp.core.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookingapp.core.ui.ThemedPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropdownMenu(
    items: List<String>,
    onItemSelected: (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(items[0]) }
    onItemSelected(selectedItem)

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = selectedItem,
                onValueChange = { selectedItem = it; onItemSelected(it) },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = isExpanded
                    )
                })
            ExposedDropdownMenu(
                modifier = Modifier.fillMaxWidth(),
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }) {
                items.forEachIndexed { index, text ->
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        text = { Text(text = text) },
                        onClick = {
                            selectedItem = items[index]
                            isExpanded = false
                            onItemSelected(selectedItem)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun DropdownMenuExamplePreview() {
    val items = listOf("Item 1", "Item 2", "Item 3")
    var selectedItem by remember { mutableStateOf(items.first()) }
    ThemedPreview {
        MyDropdownMenu(items = items) {

        }
    }
}

