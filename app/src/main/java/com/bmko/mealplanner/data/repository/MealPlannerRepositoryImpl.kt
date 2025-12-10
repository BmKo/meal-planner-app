package com.bmko.mealplanner.data.repository

import com.bmko.mealplanner.domain.models.Meal
import com.bmko.mealplanner.domain.repository.MealPlannerRepository
import com.bmko.mealplanner.domain.util.Resource
import javax.inject.Inject

class MealPlannerRepositoryImpl @Inject constructor() : MealPlannerRepository {
    override suspend fun getMealsForRotation(rotation: String): Resource<List<Meal>> {
        return try {
            // TODO: Replace with real data source
            Resource.Success(
                getSampleMeals(rotation)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

    private fun getSampleMeals(rotation: String): List<Meal> {
        return when (rotation) {
            "Week 1" -> listOf(
                Meal(
                    id = 1,
                    name = "Vegetarian Stir Fry"
                ),
                Meal(
                    id = 2,
                    name = "Shrimp Salad"
                ),
                Meal(
                    id = 3,
                    name = "Chilli Mac and Cheese"
                ),
                Meal(
                    id = 4,
                    name = "Butter Chicken"
                ),
                Meal(
                    id = 5,
                    name = "Vegetarian Fajitas"
                )
            )

            "Week 2" -> listOf(
                Meal(
                    id = 6,
                    name = "Sausage Gnocchi"
                ),
                Meal(
                    id = 7,
                    name = "Sushi Bowl"
                ),
                Meal(
                    id = 8,
                    name = "Vegetarian Nachos"
                ),
                Meal(
                    id = 9,
                    name = "Mushroom Chow Mein"
                ),
                Meal(
                    id = 10,
                    name = "Pasta Bake"
                )
            )

            else -> emptyList()
        }
    }
}