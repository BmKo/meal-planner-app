package com.bmko.mealplanner.data.remote.dtos

data class MealDto(
    val id: String,
    val name: String,
    val imageId: String?,
    val isMarkedDone: Boolean
)