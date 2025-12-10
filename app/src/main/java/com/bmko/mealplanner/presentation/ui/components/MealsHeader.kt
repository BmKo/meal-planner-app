package com.bmko.mealplanner.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MealsHeader(selectedCount: Int, totalCount: Int, onAddMeal: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Meals", style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "$selectedCount of $totalCount",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(start = 16.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary, shape = CircleShape
                )
                .padding(horizontal = 12.dp, vertical = 4.dp),
            color = MaterialTheme.colorScheme.onPrimary
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = onAddMeal, modifier = Modifier) {
            Icon(
                imageVector = Icons.Default.Add, contentDescription = "Add Meal"
            )
        }
    }
}