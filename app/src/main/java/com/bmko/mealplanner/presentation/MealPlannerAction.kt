package com.bmko.mealplanner.presentation

import com.bmko.mealplanner.domain.models.Rotation

sealed interface MealPlannerAction {
    data class GetMeals(val rotationId: String) : MealPlannerAction
    data object GetRotations : MealPlannerAction
    data class AddRotation(val rotationName: String) : MealPlannerAction
    data class SelectRotation(val rotation: Rotation) : MealPlannerAction
    data class UpdateMealDoneStatus(val mealId: String, val isDone: Boolean) : MealPlannerAction
    data class AddMeal(val mealName: String) : MealPlannerAction
    data class DeleteMeal(val mealId: String) : MealPlannerAction
    data class RenameMeal(val mealId: String, val newName: String) : MealPlannerAction
}