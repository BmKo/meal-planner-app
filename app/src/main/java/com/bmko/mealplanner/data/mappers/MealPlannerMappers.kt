package com.bmko.mealplanner.data.mappers

import com.bmko.mealplanner.data.remote.dtos.IngredientDto
import com.bmko.mealplanner.data.remote.dtos.MealDto
import com.bmko.mealplanner.data.remote.dtos.RotationDto
import com.bmko.mealplanner.domain.models.Ingredient
import com.bmko.mealplanner.domain.models.Meal
import com.bmko.mealplanner.domain.models.Rotation

fun List<MealDto>.toMeals(): List<Meal> {
    return this.map { dto ->
        dto.toMeal()
    }
}

fun List<RotationDto>.toRotations(): List<Rotation> {
    return this.map { dto ->
        dto.toRotation()
    }
}

fun List<IngredientDto>.toIngredients(): List<Ingredient> {
    return this.map { dto ->
        dto.toIngredient()
    }
}

fun MealDto.toMeal(): Meal {
    return Meal(
        id = id,
        name = name,
        // TODO: Map imageResId properly when supported
        isMarkedDone = isMarkedDone
    )
}

fun IngredientDto.toIngredient(): Ingredient {
    return Ingredient(
        id = id,
        name = name,
        quantity = quantity,
        isAcquired = isAcquired
    )
}

fun RotationDto.toRotation(): Rotation {
    return Rotation(
        id = id,
        name = name
    )
}
