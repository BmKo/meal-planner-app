package com.bmko.mealplanner.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun TextInputDialog(
    title: String,
    label: String,
    buttonLabel: String,
    startingText: String = "",
    onConfirm: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    val input = remember { mutableStateOf(startingText) }

    AlertDialog(
        title = { Text(title) },
        text = {
            OutlinedTextField(
                value = input.value,
                onValueChange = { input.value = it },
                label = { Text(label) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(onClick = {
                onConfirm(input.value)
            }) {
                Text(buttonLabel)
            }
        },
        dismissButton = {
            Button(onClick = onDismissRequest) {
                Text("Cancel")
            }
        }
    )
}