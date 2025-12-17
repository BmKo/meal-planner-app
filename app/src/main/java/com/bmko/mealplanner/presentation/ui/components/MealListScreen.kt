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
import com.bmko.mealplanner.domain.models.Rotation
import com.bmko.mealplanner.presentation.MealPlannerAction
import com.bmko.mealplanner.presentation.MealPlannerState
import com.bmko.mealplanner.presentation.MealPlannerViewModel
import com.bmko.mealplanner.presentation.ui.theme.MealPlannerTheme

@Composable
fun MealListScreenRoot(
    viewModel: MealPlannerViewModel = hiltViewModel()
) {
    MealListScreen(
        state = viewModel.state,
        actions = viewModel::onAction
    )
}

@Composable
fun MealListScreen(
    state: MealPlannerState,
    actions: (MealPlannerAction) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            RotationSelector(
                rotations = state.rotations,
                selectedRotation = state.selectedRotation,
                onRotationSelected = { rotation ->
                    actions(MealPlannerAction.SelectRotation(rotation))
                    actions(MealPlannerAction.GetMeals(rotation.id))
                },
                onNewRotationAdded = { rotation -> actions(MealPlannerAction.AddRotation(rotation)) }
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            GenerateShoppingListButton({ /* TODO: Make functional */ })
            MealsHeader(
                selectedCount = state.meals.sumOf { if (it.isMarkedDone) 1 else 0 },
                totalCount = state.meals.size,
                onAddMeal = {/* TODO: Make functional */ }
            )
            LazyColumn {
                items(state.meals) { meal ->
                    MealCard(
                        mealName = meal.name,
                        mealImage = meal.imageResId,
                        isSelected = meal.isMarkedDone,
                        onSelectionChange = { selection ->
                            actions(MealPlannerAction.UpdateMealDoneStatus(meal.id, selection))
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
    val state = MealPlannerState(
        meals = listOf(
            Meal("1", "Spicy Penne Pasta", com.bmko.mealplanner.R.drawable.sample_meal_image),
            Meal("2", "Grilled Chicken Salad", isMarkedDone = true),
            Meal("3", "Vegetable Stir Fry")
        ),
        selectedRotation = Rotation("1", "Week 1"),
        rotations = listOf(Rotation("1", "Week 1"))
    )

    MealPlannerTheme {
        MealListScreen(
            state = state,
            actions = {}
        )
    }
}