package com.bmko.mealplanner.presentation

sealed interface MealPlannerAction {
    data class GetMeals(val rotation: String) : MealPlannerAction
    data object GetRotations : MealPlannerAction
    data class AddRotation(val rotation: String) : MealPlannerAction
    data class SelectRotation(val rotation: String) : MealPlannerAction
    data class UpdateMealDoneStatus(val mealId: Int, val isDone: Boolean) : MealPlannerAction
}