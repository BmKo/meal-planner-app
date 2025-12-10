package com.bmko.mealplanner.di

import com.bmko.mealplanner.data.repository.MealPlannerRepositoryImpl
import com.bmko.mealplanner.domain.repository.MealPlannerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMealPlannerRepository(
        mealPlannerRepositoryImpl: MealPlannerRepositoryImpl
    ): MealPlannerRepository
}
