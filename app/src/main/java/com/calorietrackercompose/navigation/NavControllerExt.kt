package com.calorietrackercompose.navigation

import androidx.navigation.NavController
import com.calorietrackercompose.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}