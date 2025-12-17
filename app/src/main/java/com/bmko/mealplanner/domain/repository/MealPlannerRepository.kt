package com.bmko.mealplanner.domain.repository

import com.bmko.mealplanner.domain.models.Meal
import com.bmko.mealplanner.domain.models.Rotation
import com.bmko.mealplanner.domain.util.Resource

interface MealPlannerRepository {
    suspend fun getMealsForRotation(rotationId: String): Resource<List<Meal>>
    suspend fun getRotations(): Resource<List<Rotation>>
}
