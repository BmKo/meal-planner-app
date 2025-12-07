package com.bmko.mealplanner.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bmko.mealplanner.ui.theme.MealPlannerTheme

@Composable
fun MealListScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = { RotationSelector() }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                // TODO: Make functional
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Generate Shopping List")
            }

            MealsHeader(selectedCount = 0, totalCount = 5, onAddMeal = {})

            // TODO: Make actual list of cards
            LazyColumn {
                items(5) {
                    MealCard()
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun MealListScreenPreview() {
    MealPlannerTheme {
        MealListScreen()
    }
}