package com.github.ameeramjed.chart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ameeramjed.chart.model.ChartData
import com.github.ameeramjed.chart.theme.DoughnutChartTheme
import com.github.ameeramjed.chart.theme.dimens


@Composable
fun ItemInfoChart(
    state: ChartData,
    sizeBoxColor: Int = 20,
    valueTextString: TextStyle = MaterialTheme.typography.bodyLarge.copy(
        MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold
    ),
    labelTextString: TextStyle = MaterialTheme.typography.bodySmall.copy(
        color = MaterialTheme.colorScheme.secondary,
    ),
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        Box(
            modifier = Modifier
                .size(sizeBoxColor.dp)
                .clip(MaterialTheme.shapes.extraSmall)
                .background(state.color)
                .padding(horizontal = MaterialTheme.dimens.space4),
        )

        Column(
            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.space8),
        ) {
            Text(
                text = state.getPercentageValue(),
                style = valueTextString
            )
            Text(
                text = state.label,
                style = labelTextString
            )
        }
    }
}



@Composable
@Preview(showBackground = true)
private fun ItemInfoChartPreview() {
    DoughnutChartTheme {
        ItemInfoChart(
            ChartData("Done", 30F, Color.Blue)
        )
    }
}
