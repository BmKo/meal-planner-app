package com.bmko.mealplanner.domain.repository

import com.bmko.mealplanner.domain.models.Meal
import com.bmko.mealplanner.domain.util.Resource

interface MealPlannerRepository {
    suspend fun getMealsForRotation(rotation: String): Resource<List<Meal>>
}
