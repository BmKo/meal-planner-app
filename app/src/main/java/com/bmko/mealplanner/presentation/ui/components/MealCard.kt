package com.bmko.mealplanner.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bmko.mealplanner.R

@Composable
fun MealCard(
    mealName: String,
    mealImage: Int,
    isSelected: Boolean,
    onSelectionChange: (Boolean) -> Unit,
    onRenameMeal: (String) -> Unit,
    onDeleteMeal: () -> Unit
) {
    val openRenameMealDialog = remember { mutableStateOf(false) }
    val openDeleteMealDialog = remember { mutableStateOf(false) }

    if (openRenameMealDialog.value) {
        TextInputDialog(
            title = "Rename Meal",
            label = "Meal Name",
            buttonLabel = "Rename",
            startingText = mealName,
            onConfirm = { newName ->
                onRenameMeal(newName)
                openRenameMealDialog.value = false
            },
            onDismissRequest = {
                openRenameMealDialog.value = false
            }
        )
    }

    if (openDeleteMealDialog.value) {
        ConfirmationDialog(
            title = "Delete Meal",
            confirmationMessage = "Are you sure you want to delete this meal?",
            onConfirm = {
                onDeleteMeal()
                openDeleteMealDialog.value = false
            },
            onDismissRequest = {
                openDeleteMealDialog.value = false
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                painter = painterResource(id = mealImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = if (isSelected) {
                    ColorFilter.tint(
                        color = Color.LightGray,
                        blendMode = BlendMode.Multiply
                    )
                } else {
                    null
                }
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (isSelected) {
                        Text(
                            text = mealName,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                textDecoration = TextDecoration.LineThrough
                            )
                        )
                    } else {
                        Text(
                            text = mealName, style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Row {
                        Checkbox(
                            checked = isSelected,
                            onCheckedChange = onSelectionChange
                        )
                        MealMoreMenu(
                            onRenameClicked = { openRenameMealDialog.value = true },
                            onDeleteClicked = { openDeleteMealDialog.value = true })
                    }
                }
            }
        }
    }
}

@Composable
private fun MealMoreMenu(onRenameClicked: () -> Unit, onDeleteClicked: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(
            onClick = { expanded = true }
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Rename") },
                onClick = {
                    onRenameClicked()
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Delete") },
                onClick = {
                    onDeleteClicked()
                    expanded = false
                }
            )
        }
    }
}

@Preview
@Composable
fun MealCardNotSelectedPreview() {
    MealCard(
        mealName = "Spicy Penne Pasta",
        mealImage = R.drawable.placeholder_meal_image,
        isSelected = false,
        onSelectionChange = {},
        onRenameMeal = {},
        onDeleteMeal = {}
    )
}

@Preview
@Composable
fun MealCardSelectedPreview() {
    MealCard(
        mealName = "Spicy Penne Pasta",
        mealImage = R.drawable.placeholder_meal_image,
        isSelected = true,
        onSelectionChange = {},
        onRenameMeal = {},
        onDeleteMeal = {}
    )
}
