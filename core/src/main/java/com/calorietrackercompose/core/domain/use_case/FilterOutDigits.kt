package com.calorietrackercompose.core.domain.use_case

class FilterOutDigits {

    operator fun invoke(text: String) = text.filter { it.isDigit() }
}