package com.bmko.mealplanner.data.repository

import com.bmko.mealplanner.BuildConfig
import com.bmko.mealplanner.data.mappers.toMeals
import com.bmko.mealplanner.data.mappers.toRotations
import com.bmko.mealplanner.data.remote.MealPlannerApi
import com.bmko.mealplanner.domain.models.Meal
import com.bmko.mealplanner.domain.models.Rotation
import com.bmko.mealplanner.domain.repository.MealPlannerRepository
import com.bmko.mealplanner.domain.util.Resource
import javax.inject.Inject

class MealPlannerRepositoryImpl @Inject constructor(private val api: MealPlannerApi) :
    MealPlannerRepository {

    override suspend fun getMealsForRotation(rotationId: String): Resource<List<Meal>> {
        return try {
            val response = api.getMealsForRotation(rotationId)
            Resource.Success(response.toMeals())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun getRotations(): Resource<List<Rotation>> {
        return try {
            val response = api.getRotations(BuildConfig.PLAN_ID) // TODO: Replace when multiple plans are supported
            Resource.Success(response.toRotations())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}
