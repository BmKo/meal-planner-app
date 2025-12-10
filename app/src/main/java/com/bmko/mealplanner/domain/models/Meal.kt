package com.bmko.mealplanner.domain.models

import com.bmko.mealplanner.R

data class Meal(
    val id: Int,
    val name: String,
    val imageResId: Int = R.drawable.placeholder_meal_image,
    val isMarkedDone: Boolean = false,
    val ingredients: List<Ingredient> = emptyList()
)
