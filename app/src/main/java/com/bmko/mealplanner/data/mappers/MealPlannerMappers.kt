package com.bmko.mealplanner.data.mappers

import com.bmko.mealplanner.data.remote.dtos.MealDto
import com.bmko.mealplanner.data.remote.dtos.RotationDto
import com.bmko.mealplanner.domain.models.Meal
import com.bmko.mealplanner.domain.models.Rotation

fun List<MealDto>.toMeals(): List<Meal> {
    return this.map { dto ->
        Meal(
            id = dto.id,
            name = dto.name,
            // TODO: Map imageResId properly when supported
            isMarkedDone = dto.isMarkedDone
        )
    }
}

fun List<RotationDto>.toRotations(): List<Rotation> {
    return this.map { dto ->
        Rotation(
            id = dto.id,
            name = dto.name
        )
    }
}
