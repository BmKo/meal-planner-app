package com.bmko.mealplanner.domain.repository

import com.bmko.mealplanner.domain.models.Ingredient
import com.bmko.mealplanner.domain.models.Meal
import com.bmko.mealplanner.domain.models.Rotation
import com.bmko.mealplanner.domain.util.Resource

interface MealPlannerRepository {
    suspend fun getMealsForRotation(rotationId: String): Resource<List<Meal>>
    suspend fun getRotations(): Resource<List<Rotation>>
    suspend fun getIngredientsForMeal(mealId: String): Resource<List<Ingredient>>
    suspend fun markMealDone(mealId: String, isDone: Boolean): Resource<Meal>
    suspend fun addRotation(rotationName: String): Resource<Rotation>
    suspend fun addMeal(rotationId: String, mealName: String): Resource<Meal>
}
