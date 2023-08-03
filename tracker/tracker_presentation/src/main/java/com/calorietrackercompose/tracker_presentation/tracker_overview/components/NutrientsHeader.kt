package com.calorietrackercompose.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.calorietrackercompose.core_ui.CarbColor
import com.calorietrackercompose.core_ui.FatColor
import com.calorietrackercompose.core_ui.LocalSpacing
import com.calorietrackercompose.core_ui.ProteinColor
import com.calorietrackercompose.tracker_presentation.components.UnitDisplay
import com.calorietrackercompose.tracker_presentation.tracker_overview.TrackerOverviewState

@Composable
fun NutrientsHeader(
    state: TrackerOverviewState,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    val animatedCalorieCount = animateIntAsState(
        targetValue = state.totalCalories
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomStart = 50.dp,
                    bottomEnd = 50.dp
                )
            )
            .background(MaterialTheme.colors.primary)
            .padding(
                horizontal = spacing.spaceLarge,
                vertical = spacing.spaceExtraLarge
            )
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            UnitDisplay(
                amount = animatedCalorieCount.value,
                unit = stringResource(id = com.calorietrackercompose.core.R.string.kcal),
                amountColor = MaterialTheme.colors.onPrimary,
                amountTextSize = 40.sp,
                unitColor = MaterialTheme.colors.onPrimary,
                modifier = modifier.align(Alignment.Bottom)
            )
            Column {
                Text(
                    text = stringResource(id = com.calorietrackercompose.core.R.string.your_goal),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onPrimary
                )
                UnitDisplay(
                    amount = state.caloriesGoal,
                    unit = stringResource(id = com.calorietrackercompose.core.R.string.kcal),
                    amountColor = MaterialTheme.colors.onPrimary,
                    amountTextSize = 40.sp,
                    unitColor = MaterialTheme.colors.onPrimary,
                )
            }
        }
        Spacer(modifier = modifier.height(spacing.spaceSmall))
        NutrientsBar(
            carbs = state.totalCarbs,
            protein = state.totalProtein,
            fat = state.totalFat,
            calories = state.totalCalories,
            calorieGoal = state.caloriesGoal,
            modifier = modifier
                .fillMaxWidth()
                .height(30.dp)
        )
        Spacer(modifier = modifier.height(spacing.spaceLarge))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NutrientBarInfo(
                value = state.totalCarbs,
                goal = state.carbsGoal,
                name = stringResource(id = com.calorietrackercompose.core.R.string.carbs),
                color = CarbColor,
                modifier = modifier.size(90.dp)
            )
            NutrientBarInfo(
                value = state.totalProtein,
                goal = state.proteinGoal,
                name = stringResource(id = com.calorietrackercompose.core.R.string.protein),
                color = ProteinColor,
                modifier = modifier.size(90.dp)
            )
            NutrientBarInfo(
                value = state.totalFat,
                goal = state.fatGoal,
                name = stringResource(id = com.calorietrackercompose.core.R.string.fat),
                color = FatColor,
                modifier = modifier.size(90.dp)
            )
        }
    }
}