package com.calorietrackercompose.tracker_presentation.search

import com.calorietrackercompose.tracker_domain.model.TrackableFood

data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)
