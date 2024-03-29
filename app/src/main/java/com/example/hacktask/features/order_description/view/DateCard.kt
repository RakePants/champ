package com.example.hacktask.features.order_description.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hacktask.features.order_description.presentation.OrderDescriptionViewModel
import com.example.hacktask.ui.theme.Heading2

@Composable
fun DateCard(
    modifier: Modifier = Modifier,
    viewModel: OrderDescriptionViewModel,
    backgroundColor: Color,
    date: String,
) {
    Row(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp),
            )
            .padding(5.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,

    ) {
        Icon(
            Icons.Rounded.DateRange,
            contentDescription = "calendar",
            tint = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = date,
            style = MaterialTheme.typography.Heading2,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun DateCard1(
    modifier: Modifier = Modifier,
    viewModel: OrderDescriptionViewModel,
    date: String,
) {
    DateCard(
        modifier = modifier,
        viewModel = viewModel,
        date = date,
        backgroundColor = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun DateCard2(
    modifier: Modifier = Modifier,
    viewModel: OrderDescriptionViewModel,
    date: String,
) {
    DateCard(
        modifier = modifier,
        viewModel = viewModel,
        date = date,
        backgroundColor = MaterialTheme.colorScheme.primary
    )
}
