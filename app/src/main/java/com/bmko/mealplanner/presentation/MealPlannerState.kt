package com.bmko.mealplanner.presentation

import com.bmko.mealplanner.domain.models.Meal
import com.bmko.mealplanner.domain.models.Rotation

data class MealPlannerState(
    val meals: List<Meal> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedRotation: Rotation? = null,
    val rotations: List<Rotation> = emptyList()
)
