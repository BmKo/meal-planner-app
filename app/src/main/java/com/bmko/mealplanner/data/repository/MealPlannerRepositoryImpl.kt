package com.bmko.mealplanner.data.repository

import com.bmko.mealplanner.BuildConfig
import com.bmko.mealplanner.data.mappers.toIngredients
import com.bmko.mealplanner.data.mappers.toMeal
import com.bmko.mealplanner.data.mappers.toMeals
import com.bmko.mealplanner.data.mappers.toRotation
import com.bmko.mealplanner.data.mappers.toRotations
import com.bmko.mealplanner.data.remote.MealPlannerApi
import com.bmko.mealplanner.domain.models.Ingredient
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
            val response =
                api.getRotations(BuildConfig.PLAN_ID) // TODO: Replace when multiple plans are supported
            Resource.Success(response.toRotations())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun getIngredientsForMeal(mealId: String): Resource<List<Ingredient>> {
        return try {
            val response = api.getIngredientsForMeal(mealId)
            Resource.Success(response.toIngredients())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun markMealDone(mealId: String, isDone: Boolean): Resource<Meal> {
        return try {
            val response = api.markMealDone(mealId, isDone)
            Resource.Success(response.toMeal())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun addRotation(rotationName: String): Resource<Rotation> {
        return try {
            val response = api.createRotation(BuildConfig.PLAN_ID, rotationName) // TODO: Replace when multiple plans are supported
            Resource.Success(response.toRotation())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun addMeal(rotationId: String, mealName: String): Resource<Meal> {
        return try {
            val response = api.createMeal(rotationId, mealName)
            Resource.Success(response.toMeal())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun deleteMeal(mealId: String): Resource<Unit> {
        return try {
            api.deleteMeal(mealId)
            Resource.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun renameMeal(
        mealId: String,
        newName: String
    ): Resource<Meal> {
        return try {
            val response = api.renameMeal(mealId, newName)
            Resource.Success(response.toMeal())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}
