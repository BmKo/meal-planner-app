package com.bmko.mealplanner.data.remote.dtos

data class IngredientDto(
    val id: String,
    val name: String,
    val quantity: String,
    val isAcquired: Boolean
)
