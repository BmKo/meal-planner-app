package com.bmko.mealplanner.domain.models

data class Ingredient(
    val id: Int,
    val name: String,
    val quantity: String, // TODO: Improve representation of quantity
    val isAcquired: Boolean = false
)
