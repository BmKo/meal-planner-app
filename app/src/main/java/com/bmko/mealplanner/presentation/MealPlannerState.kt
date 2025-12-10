package com.bmko.mealplanner.presentation

import com.bmko.mealplanner.domain.models.Meal

data class MealPlannerState(
    val meals: List<Meal> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedRotation: String? = null,
    val rotations: List<String> = emptyList()
)
