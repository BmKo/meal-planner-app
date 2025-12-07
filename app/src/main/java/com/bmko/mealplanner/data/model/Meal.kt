package com.bmko.mealplanner.data.model

import com.bmko.mealplanner.R

data class Meal(
    val id: Int,
    val name: String,
    val imageResId: Int = R.drawable.placeholder_meal_image
)

fun getSampleMeals(week: Int): List<Meal> {
    when (week) {
        1 -> return listOf(
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

        2 -> return listOf(
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
    }

    return emptyList()
}