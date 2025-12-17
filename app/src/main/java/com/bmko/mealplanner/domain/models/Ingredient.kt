package com.bmko.mealplanner.domain.models

data class Ingredient(
    val id: String,
    val name: String,
    val quantity: String,
    val isAcquired: Boolean = false
)
