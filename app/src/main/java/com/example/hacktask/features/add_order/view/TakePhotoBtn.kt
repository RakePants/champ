package com.example.hacktask.features.add_order.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.hacktask.R
import com.example.hacktask.ui.theme.Heading2
import com.example.hacktask.ui.theme.dimens

@Composable
fun TakePhotoBtn(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val stroke = Stroke(
        width = 4f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )
    val borderColor = MaterialTheme.colorScheme.onPrimary
    val cornerRadiusBtn = MaterialTheme.dimens.buttonCornerRadius

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = MaterialTheme.dimens.gapBetweenComponentScreen * 2,
                end = MaterialTheme.dimens.gapBetweenComponentScreen * 2,
            )
            .drawBehind {
                drawRoundRect(
                    color = borderColor,
                    style = stroke,
                    cornerRadius = CornerRadius(cornerRadiusBtn.toPx())
                )
            }
            .clip(RoundedCornerShape(MaterialTheme.dimens.buttonCornerRadius))
            .clickable { onClick() },
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimens.buttonCornerRadius),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.take_photo),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.Heading2,
                textAlign = TextAlign.Center
            )
        }
    }
}