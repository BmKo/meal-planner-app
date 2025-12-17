package com.bmko.mealplanner.data.remote

import com.bmko.mealplanner.BuildConfig
import com.bmko.mealplanner.data.remote.dtos.MealDto
import com.bmko.mealplanner.data.remote.dtos.RotationDto
import io.appwrite.Query
import io.appwrite.services.TablesDB
import javax.inject.Inject

class MealPlannerApi @Inject constructor(private val tablesDB: TablesDB) {
    val databaseId = BuildConfig.DATABASE_ID
    val mealsTableId = BuildConfig.MEALS_TABLE_ID
    val rotationsTableId = BuildConfig.ROTATIONS_TABLE_ID

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
}