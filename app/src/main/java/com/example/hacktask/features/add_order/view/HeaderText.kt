package com.example.hacktask.features.add_order.view

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.hacktask.R
import com.example.hacktask.ui.theme.Heading1

@Composable
fun HeaderText() {
    Text(
        text = stringResource(id = R.string.creating_order),
        style = MaterialTheme.typography.Heading1,
        color = MaterialTheme.colorScheme.onPrimary
    )
}