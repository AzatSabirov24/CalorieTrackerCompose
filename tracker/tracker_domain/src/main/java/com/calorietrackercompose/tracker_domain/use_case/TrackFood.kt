package com.calorietrackercompose.tracker_domain.use_case

import com.calorietrackercompose.tracker_domain.model.MealType
import com.calorietrackercompose.tracker_domain.model.TrackableFood
import com.calorietrackercompose.tracker_domain.model.TrackedFood
import com.calorietrackercompose.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import kotlin.math.roundToInt

class TrackFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(
        food: TrackableFood,
        amount: Int,
        mealType: MealType,
        localDate: LocalDate
    ) {
        food.apply {
            repository.insertTrackedFood(
                TrackedFood(
                    name = name,
                    carbs = ((carbsPer100g / 100f) * amount).roundToInt(),
                    protein = ((proteinPer100g / 100f) * amount).roundToInt(),
                    fat = ((fatPer100g / 100f) * amount).roundToInt(),
                    calories = ((caloriesPer100g / 100f) * amount).roundToInt(),
                    imageUrl = imageUrl,
                    mealType = mealType,
                    amount = amount,
                    date = localDate
                )
            )
        }
    }
}