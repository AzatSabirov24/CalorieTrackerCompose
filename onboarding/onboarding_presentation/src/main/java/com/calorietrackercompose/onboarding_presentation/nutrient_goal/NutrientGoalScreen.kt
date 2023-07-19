package com.calorietrackercompose.onboarding_presentation.nutrient_goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.calorietrackercompose.core.R
import com.calorietrackercompose.core.util.UiEvent
import com.calorietrackercompose.core_ui.LocalSpacing
import com.calorietrackercompose.onboarding_presentation.components.ActionButton
import com.calorietrackercompose.onboarding_presentation.components.UnitTextField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NutrientGoalScreen(
    scaffoldState: ScaffoldState,
    modifier: Modifier = Modifier,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: NutrientGoalViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }

                else -> Unit
            }
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(
                    id = R.string.what_are_your_nutrient_goals
                ),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = viewModel.state.carbRatio,
                onValueChange = {
                    viewModel.onEvent(NutrientGoalEvent.OnCarbRatioEnter(it))
                },
                unitString = stringResource(id = R.string.carbs)
            )
            Spacer(modifier = modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = viewModel.state.proteinRatio,
                onValueChange = {
                    viewModel.onEvent(NutrientGoalEvent.OnProteinRatioEnter(it))
                },
                unitString = stringResource(id = R.string.protein)
            )
            Spacer(modifier = modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = viewModel.state.fatRatio,
                onValueChange = {
                    viewModel.onEvent(NutrientGoalEvent.OnFatRatioEnter(it))
                },
                unitString = stringResource(id = R.string.fat)
            )
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = {
                viewModel.onEvent(NutrientGoalEvent.OnNextClick)
            },
            modifier = modifier.align(Alignment.BottomEnd)
        )
    }
}