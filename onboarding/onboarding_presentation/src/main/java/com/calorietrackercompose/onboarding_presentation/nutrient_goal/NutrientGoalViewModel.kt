package com.calorietrackercompose.onboarding_presentation.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calorietrackercompose.core.domain.preferences.Preferences
import com.calorietrackercompose.core.domain.use_case.FilterOutDigits
import com.calorietrackercompose.core.navigation.Route
import com.calorietrackercompose.core.util.UiEvent
import com.calorietrackercompose.onboarding_domain.use_case.ValidateNutrients
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrient: ValidateNutrients
) : ViewModel() {

    var state by mutableStateOf(NutrientGoalState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NutrientGoalEvent) {
        when (event) {
            is NutrientGoalEvent.OnCarbRatioEnter -> {
                state = state.copy(carbRatio = filterOutDigits.invoke(event.ratio))
            }

            is NutrientGoalEvent.OnProteinRatioEnter -> {
                state = state.copy(proteinRatio = filterOutDigits.invoke(event.ratio))
            }

            is NutrientGoalEvent.OnFatRatioEnter -> {
                state = state.copy(fatRatio = filterOutDigits.invoke(event.ratio))
            }

            is NutrientGoalEvent.OnNextClick -> {
                val result = validateNutrient(
                    carbRatioText = state.carbRatio,
                    proteinRatioText = state.proteinRatio,
                    fatRatioText = state.fatRatio
                )
                when(result) {
                    is ValidateNutrients.Result.Success -> {
                        preferences.saveCarbRatio(ratio = result.carbRatio)
                        preferences.saveProteinRatio(ratio = result.proteinRatio)
                        preferences.saveFatRatio(ratio = result.fatRatio)
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.Navigate(Route.TRACKER_OVERVIEW))
                        }
                    }
                    is ValidateNutrients.Result.Error -> {
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.ShowSnackBar(result.message))
                        }
                    }
                }
            }
        }
    }
}