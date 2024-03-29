package com.example.hacktask.features.order.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hacktask.ui.theme.Heading1

@Composable
fun LogoText(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = modifier
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(5.dp),
                )
                .padding(3.dp),

            text = "City",
            style = MaterialTheme.typography.Heading1,
        )
        Text(
            text = "Watch",
            style = MaterialTheme.typography.Heading1,
            color = MaterialTheme.colorScheme.background
        )
    }
}