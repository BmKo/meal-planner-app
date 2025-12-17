package com.bmko.mealplanner.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bmko.mealplanner.domain.models.Rotation
import com.bmko.mealplanner.domain.repository.MealPlannerRepository
import com.bmko.mealplanner.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealPlannerViewModel @Inject constructor(
    private val repository: MealPlannerRepository
) : ViewModel() {

    var state by mutableStateOf(MealPlannerState())
        private set

    init {
        getRotations()
    }

    fun onAction(action: MealPlannerAction) {
        when (action) {
            is MealPlannerAction.GetMeals -> getMeals(action.rotationId)
            is MealPlannerAction.GetRotations -> getRotations()
            is MealPlannerAction.AddRotation -> addRotation(action.rotationName)
            is MealPlannerAction.SelectRotation -> selectRotation(action.rotation)
            is MealPlannerAction.UpdateMealDoneStatus -> updateMealDoneStatus(
                action.mealId,
                action.isDone
            )
        }
    }

    private fun getMeals(rotation: String) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            when (val result = repository.getMealsForRotation(rotation)) {
                is Resource.Success -> {
                    state = state.copy(
                        meals = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        meals = emptyList(),
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }

    private fun getRotations() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            when (val result = repository.getRotations()) {
                is Resource.Success -> {
                    state = state.copy(
                        rotations = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        rotations = emptyList(),
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }

    private fun addRotation(rotationName: String) {
        viewModelScope.launch {
            // TODO: Replace with repository call to persist
            // TODO: Add error handling for duplicates
            val updatedRotations = state.rotations + Rotation(
                rotationName,
                rotationName
            )
            state = state.copy(
                rotations = updatedRotations
            )
        }
    }

    private fun selectRotation(rotation: Rotation) {
        state = state.copy(
            selectedRotation = rotation
        )
    }

    private fun updateMealDoneStatus(mealId: String, isDone: Boolean) {
        viewModelScope.launch {
            // TODO: Replace with repository call to persist
            val updatedMeals = state.meals.map { meal ->
                if (meal.id == mealId) {
                    meal.copy(isMarkedDone = isDone)
                } else {
                    meal
                }
            }

            state = state.copy(
                meals = updatedMeals
            )
        }
    }
}
