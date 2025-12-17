package com.bmko.mealplanner.di

import android.content.Context
import com.bmko.mealplanner.BuildConfig
import com.bmko.mealplanner.data.remote.MealPlannerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.appwrite.Client
import io.appwrite.services.Account
import io.appwrite.services.TablesDB
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppwriteModule {

    @Provides
    @Singleton
    fun provideAppwriteClient(@ApplicationContext context: Context): Client =
        Client(context)
            .setEndpoint(BuildConfig.APPWRITE_ENDPOINT)
            .setProject(BuildConfig.APPWRITE_PROJECT_ID)

    @Provides
    @Singleton
    fun provideAccount(client: Client): Account = Account(client)

    @Provides
    @Singleton
    fun provideDatabase(client: Client): TablesDB = TablesDB(client)

    @Provides
    @Singleton
    fun provideMealPlannerApi(tablesDB: TablesDB): MealPlannerApi = MealPlannerApi(tablesDB)
}
