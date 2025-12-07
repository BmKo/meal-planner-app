package com.bmko.mealplanner.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MealCard(mealName: String, mealImage: Int, isSelected: Boolean, onSelectionChange: (Boolean) -> Unit) {
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
                painter = painterResource(id = mealImage),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = mealName, style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Checkbox(
                        checked = isSelected,
                        onCheckedChange = onSelectionChange

                    )

                    // TODO: Make stylized version of checkbox
//                    IconButton(
//                        onClick = { onSelectionChange(!isSelected) },
//                        modifier = Modifier.size(24.dp)
//                    ) {
//                        if (isSelected) {
//                            // Placeholder for checked state
//                            Icon(
//                                imageVector = Icons.Default.RadioButtonChecked,
//                                contentDescription = "Marked as done"
//                            )
//                        } else {
//                            Icon(
//                                imageVector = Icons.Default.RadioButtonUnchecked,
//                                contentDescription = "Mark as done"
//                            )
//                        }
//                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MealCardPreview() {
    MealCard(
        mealName = "Spicy Penne Pasta",
        mealImage = com.bmko.mealplanner.R.drawable.placeholder_meal_image,
        isSelected = false,
        onSelectionChange = {}
    )
}
