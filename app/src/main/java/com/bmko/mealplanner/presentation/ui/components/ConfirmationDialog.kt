package com.bmko.mealplanner.presentation.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ConfirmationDialog(
    title: String,
    confirmationMessage: String,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        title = { Text(title) },
        text = {
            Text(confirmationMessage)
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(onClick = {
                onConfirm()
            }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = onDismissRequest) {
                Text("Cancel")
            }
        }
    )
}