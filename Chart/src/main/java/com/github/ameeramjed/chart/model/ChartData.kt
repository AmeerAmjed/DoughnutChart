package com.github.ameeramjed.chart.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ChartData(
    val color: Color,
    val data: Float,
    val label: String
)