package com.bmko.mealplanner.data.remote

import com.bmko.mealplanner.BuildConfig
import com.bmko.mealplanner.data.remote.dtos.IngredientDto
import com.bmko.mealplanner.data.remote.dtos.MealDto
import com.bmko.mealplanner.data.remote.dtos.RotationDto
import io.appwrite.ID
import io.appwrite.Query
import io.appwrite.services.TablesDB
import javax.inject.Inject

class MealPlannerApi @Inject constructor(private val tablesDB: TablesDB) {
    val databaseId = BuildConfig.DATABASE_ID
    val mealsTableId = BuildConfig.MEALS_TABLE_ID
    val rotationsTableId = BuildConfig.ROTATIONS_TABLE_ID
    val ingredientsTableId = BuildConfig.INGREDIENTS_TABLE_ID

    suspend fun getRotations(planId: String): List<RotationDto> {
        val response = tablesDB.listRows(
            databaseId = databaseId, tableId = rotationsTableId, queries = listOf(
                Query.equal("plan", planId),
                Query.orderAsc($$"$createdAt")
            )
        )

        return response.rows.map { row ->
            RotationDto(
                id = row.id,
                name = row.data["name"] as String
            )
        }
    }

    suspend fun getMealsForRotation(rotationId: String): List<MealDto> {
        val response = tablesDB.listRows(
            databaseId = databaseId, tableId = mealsTableId, queries = listOf(
                Query.equal("rotation", rotationId),
                Query.orderAsc($$"$createdAt")
            )
        )

        return response.rows.map { row ->
            MealDto(
                id = row.id,
                name = row.data["name"] as String,
                imageId = row.data["imageId"] as String?,
                isMarkedDone = row.data["isMarkedDone"] as Boolean
            )
        }
    }

    suspend fun getIngredientsForMeal(mealId: String): List<IngredientDto> {
        val response = tablesDB.listRows(
            databaseId = databaseId, tableId = ingredientsTableId, queries = listOf(
                Query.equal("meal", mealId)
            )
        )

        return response.rows.map { row ->
            IngredientDto(
                id = row.id,
                name = row.data["name"] as String,
                quantity = row.data["quantity"] as String,
                isAcquired = row.data["isAcquired"] as Boolean
            )
        }
    }

    suspend fun markMealDone(mealId: String, isDone: Boolean): MealDto {
        val response = tablesDB.updateRow(
            databaseId = databaseId,
            tableId = mealsTableId,
            rowId = mealId,
            data = mapOf("isMarkedDone" to isDone)
        )

        return MealDto(
            id = response.id,
            name = response.data["name"] as String,
            imageId = response.data["imageId"] as String?,
            isMarkedDone = response.data["isMarkedDone"] as Boolean
        )
    }

    suspend fun createRotation(planId: String, rotationName: String): RotationDto {
        val response = tablesDB.createRow(
            databaseId = databaseId,
            tableId = rotationsTableId,
            rowId = ID.unique(),
            data = mapOf(
                "name" to rotationName,
                "plan" to planId
            )
        )

        return RotationDto(
            id = response.id,
            name = response.data["name"] as String
        )
    }

    suspend fun createMeal(rotationId: String, mealName: String): MealDto {
        val response = tablesDB.createRow(
            databaseId = databaseId,
            tableId = mealsTableId,
            rowId = ID.unique(),
            data = mapOf(
                "name" to mealName,
                "rotation" to rotationId
            )
        )

        return MealDto(
            id = response.id,
            name = response.data["name"] as String,
            imageId = response.data["imageId"] as String?,
            isMarkedDone = response.data["isMarkedDone"] as Boolean
        )
    }
}