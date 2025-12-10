package com.bmko.mealplanner.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.bmko.mealplanner.domain.models.Meal
import com.bmko.mealplanner.presentation.MealPlannerViewModel
import com.bmko.mealplanner.presentation.ui.theme.MealPlannerTheme

@Composable
fun MealListScreenRoot(
    viewModel: MealPlannerViewModel = hiltViewModel()
) {
    MealListScreen(
        meals = viewModel.state.meals,
        getMeals = { rotation ->
            viewModel.getMeals(rotation)
        },
        selectedRotation = viewModel.state.selectedRotation,
        rotations = viewModel.state.rotations,
        addRotation = { rotation ->
            viewModel.addRotation(rotation)
        },
        selectRotation = { rotation ->
            viewModel.selectRotation(rotation)
        },
        updateMealDoneStatus = { mealId, isDone ->
            viewModel.updateMealDoneStatus(mealId, isDone)
        }
    )
}

@Composable
fun MealListScreen(
    meals: List<Meal>,
    getMeals: (String) -> Unit,
    selectedRotation: String?,
    rotations: List<String>,
    addRotation: (String) -> Unit,
    selectRotation: (String) -> Unit,
    updateMealDoneStatus: (Int, Boolean) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            RotationSelector(
                rotations = rotations,
                selectedRotation = selectedRotation,
                onRotationSelected = { rotation ->
                    selectRotation(rotation)
                    getMeals(rotation)
                },
                onNewRotationAdded = { rotation -> addRotation(rotation) })
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            GenerateShoppingListButton({ /* TODO: Make functional */ })
            MealsHeader(
                selectedCount = meals.sumOf { if (it.isMarkedDone) 1 else 0 },
                totalCount = meals.size,
                onAddMeal = {/* TODO: Make functional */}
            )
            LazyColumn {
                items(meals) { meal ->
                    MealCard(
                        mealName = meal.name,
                        mealImage = meal.imageResId,
                        isSelected = meal.isMarkedDone,
                        onSelectionChange = { selection ->
                            updateMealDoneStatus(meal.id, selection)
                        })
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
        MealListScreen(
            meals = listOf(
                Meal(1, "Spicy Penne Pasta", com.bmko.mealplanner.R.drawable.sample_meal_image),
                Meal(2, "Grilled Chicken Salad", isMarkedDone = true),
                Meal(3, "Vegetable Stir Fry")
            ),
            getMeals = {},
            selectedRotation = "Week 1",
            rotations = listOf("Week 1", "Week 2"),
            addRotation = {},
            selectRotation = {},
            updateMealDoneStatus = { _, _ -> }
        )
    }
}