package com.bmko.mealplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bmko.mealplanner.ui.components.MealListScreen
import com.bmko.mealplanner.ui.theme.MealPlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MealPlannerTheme {
                MealListScreen()
            }
        }
    }
}