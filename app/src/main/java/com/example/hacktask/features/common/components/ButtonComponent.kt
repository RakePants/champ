package com.example.hacktask.features.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.hacktask.ui.theme.Heading2
import com.example.hacktask.ui.theme.dimens

@Composable
private fun ButtonComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    status: Boolean,
    isLoading: Boolean,
    text: String,
    btnColor: Color,
    borderColor: Color,
    disabledColor: Color,
    disabledTextColor: Color,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(MaterialTheme.dimens.buttonsHeight)
            .padding(
                start = MaterialTheme.dimens.gapBetweenComponentScreen,
                end = MaterialTheme.dimens.gapBetweenComponentScreen
            ),
        onClick = {
            onClick()
        },
        enabled = status,
        shape = RoundedCornerShape(MaterialTheme.dimens.buttonCornerRadius),
        colors = ButtonDefaults.buttonColors (
            containerColor = btnColor,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = disabledColor,
            disabledContentColor = disabledTextColor,
        ),
        border = BorderStroke(MaterialTheme.dimens.buttonsWidth, color = borderColor),
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier
                    .size(MaterialTheme.dimens.buttonCornerRadius),
                color = MaterialTheme.colorScheme.onPrimary,
                strokeWidth = MaterialTheme.dimens.buttonsWidth + MaterialTheme.dimens.buttonsWidth
            )
        }
        else {
            Text(
                text = text,
                style = MaterialTheme.typography.Heading2,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun ButtonComponent1(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    status: Boolean = true,
    isLoading: Boolean = false,
    text: String,
) {
    ButtonComponent(
        modifier = modifier,
        onClick = onClick,
        status = status,
        isLoading = isLoading,
        text = text,
        btnColor = MaterialTheme.colorScheme.primary,
        borderColor = Color.Transparent,
        disabledColor = MaterialTheme.colorScheme.secondaryContainer,
        disabledTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
    )
}