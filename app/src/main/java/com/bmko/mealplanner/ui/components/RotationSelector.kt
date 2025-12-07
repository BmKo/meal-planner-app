package com.bmko.mealplanner.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RotationSelector(
    options: List<String>,
    onRotationSelected: (String) -> Unit,
    onNewRotationAdded: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val textFieldState = rememberTextFieldState(options[0])
    val openAddRotationDialog = remember { mutableStateOf(false) }

    if (openAddRotationDialog.value) {
        AddRotationDialog(
            onAddRotation = { rotationName ->
                textFieldState.setTextAndPlaceCursorAtEnd(rotationName)
                onNewRotationAdded(rotationName)
                // TODO: Handle duplicate names
                // TODO: Handle updating to new rotation list correctly
                openAddRotationDialog.value = false
                expanded = false
            },
            onDismissRequest = {
                openAddRotationDialog.value = false
            }
        )
    }

    ExposedDropdownMenuBox(
        expanded = expanded, onExpandedChange = { expanded = !expanded }) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            shadowElevation = 4.dp
        ) {
            // TODO: Look into better options
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth()
                    .padding(16.dp)
                    .statusBarsPadding(),
                state = textFieldState,
                readOnly = true,
                lineLimits = TextFieldLineLimits.SingleLine,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                    focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer
                ),
                textStyle = MaterialTheme.typography.titleMedium,
            )
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(

                    text = { Text(option, style = MaterialTheme.typography.bodyLarge) },
                    onClick = {
                        textFieldState.setTextAndPlaceCursorAtEnd(option)
                        onRotationSelected(option)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
            // TODO: Handle removing rotations
            Button(
                onClick = {
                    openAddRotationDialog.value = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Add Rotation")
            }
        }
    }
}

@Composable
fun AddRotationDialog(
    onAddRotation: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    val rotationName = remember { mutableStateOf("") }

    AlertDialog(
        title = { Text("Add New Rotation") },
        text = {
            OutlinedTextField(
                value = rotationName.value,
                onValueChange = { rotationName.value = it },
                label = { Text("Rotation Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(onClick = {
                onAddRotation(rotationName.value)
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = onDismissRequest) {
                Text("Cancel")
            }
        }
    )
}

@Preview
@Composable
fun RotationSelectorPreview() {
    val options = listOf("Week 1", "Week 2", "Week 3")
    RotationSelector(
        options = options,
        onRotationSelected = {},
        onNewRotationAdded = {}
    )
}
