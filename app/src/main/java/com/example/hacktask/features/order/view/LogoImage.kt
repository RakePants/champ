package com.example.hacktask.features.order.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hacktask.R

@Composable
fun LogoImage(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(150.dp)
            .clip(shape = RectangleShape),
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "logo"
    )
}
