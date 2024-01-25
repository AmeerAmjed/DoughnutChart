package com.github.ameeramjed.chart.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ameeramjed.chart.model.ChartData
import com.github.ameeramjed.chart.theme.DoughnutChartTheme
import com.github.ameeramjed.chart.theme.dimens
import com.github.ameeramjed.chart.util.asAngle


@Composable
fun DoughnutChart(
    state: List<ChartData>,
    modifier: Modifier = Modifier,
    chartSize: Int = 132,
    delayAnimationCart: Int = 1000
) {

    val chartAnimatable = remember {
        Animatable(-90f)
    }

    LaunchedEffect(key1 = chartAnimatable) {
        chartAnimatable.animateTo(
            targetValue = 270f,
            animationSpec = tween(
                delayMillis = delayAnimationCart,
                durationMillis = 2000
            )
        )
    }

    val currentSweepAngle = chartAnimatable.value

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimens.space16)
    ) {

        Box(
            modifier = Modifier
                .padding(MaterialTheme.dimens.space16)
                .size(chartSize.dp)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {

            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                val width = size.width
                val radius = width / 2f
                val strokeWidth = radius * .3f
                var startAngle = -90f

                for (index in 0..state.lastIndex) {

                    val chartData = state[index]
                    val sweepAngle = chartData.value.asAngle

                    if (startAngle <= currentSweepAngle) {
                        drawArc(
                            color = chartData.color,
                            startAngle = startAngle,
                            sweepAngle = sweepAngle.coerceAtMost(currentSweepAngle - startAngle),
                            useCenter = false,
                            topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
                            size = Size(width - strokeWidth, width - strokeWidth),
                            style = Stroke(strokeWidth)
                        )
                    }

                    startAngle += sweepAngle
                }
            }


        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            state.forEach { item ->
                ItemInfoChart(item)
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
private fun DoughnutChartPreview() {
    val chartDataList = listOf(
        ChartData("Todo", 50F, Color.Cyan),
        ChartData("Progress", 20F, Color.Red),
        ChartData("Done", 30F, Color.Blue),
    )

    DoughnutChartTheme {
        DoughnutChart(chartDataList)
    }
}






