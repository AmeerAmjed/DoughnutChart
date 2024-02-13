package io.github.ameeramjed.chart.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ChartData(
    val label: String,
    val value: Float,
    val color: Color,
) {
    fun getPercentageValue(): String = "${value.toInt()}%"
}