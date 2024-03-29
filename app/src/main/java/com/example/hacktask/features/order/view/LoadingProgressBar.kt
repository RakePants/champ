package com.example.hacktask.features.order.view

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hacktask.ui.theme.dimens

@Composable
fun LoadingProgressBar(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier = modifier
            .size(50.dp),
        color = MaterialTheme.colorScheme.onPrimary,
        strokeWidth = MaterialTheme.dimens.bottomGap
    )
}