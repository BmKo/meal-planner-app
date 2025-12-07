package com.bmko.mealplanner.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bmko.mealplanner.data.model.getSampleMeals
import com.bmko.mealplanner.ui.theme.MealPlannerTheme

@Composable
fun MealListScreen() {
    val options = mutableListOf("Week 1", "Week 2")
    var selectedIndex = 0

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            RotationSelector(
                options = options,
                onRotationSelected = { selectedIndex = options.indexOf(it) },
                onNewRotationAdded = { option -> options.add(option) })
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val meals = getSampleMeals(week = selectedIndex + 1)

            GenerateShoppingListButton({ /* TODO: Make functional */ })
            MealsHeader(selectedCount = 0, totalCount = meals.size, onAddMeal = {})
            LazyColumn {
                items(meals.size) { index ->
                    val meal = meals[index]
                    MealCard(
                        mealName = meal.name,
                        mealImage = meal.imageResId,
                        isSelected = false,
                        onSelectionChange = { /* TODO: Handle selection change */ }
                    )
                }
            }
        }
    }
}

@Composable
private fun GenerateShoppingListButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text("Generate Shopping List")
    }
}

@Preview(showBackground = true)
@Composable
fun MealListScreenPreview() {
    MealPlannerTheme {
        MealListScreen()
    }
}