package com.example.hacktask.features.order_description.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.hacktask.R
import com.example.hacktask.ui.theme.Buttons
import com.example.hacktask.ui.theme.Heading2
import com.example.hacktask.ui.theme.dimens

@Composable
private fun CardText(text1: String, color1: Color, text2: String, color2: Color) {
    Text(
        buildAnnotatedString {
            withStyle(style = MaterialTheme.typography.Heading2.toSpanStyle() + SpanStyle(color1)) {
                append(text1)
            }
            withStyle(style = MaterialTheme.typography.Buttons.toSpanStyle() + SpanStyle(color = color2)) {
                append(text2)
            }
        }
    , modifier = Modifier
        .fillMaxWidth()
        .padding(
            start = MaterialTheme.dimens.gapBetweenComponentScreen,
            end = MaterialTheme.dimens.gapBetweenComponentScreen,
        ),
    )
}

@Composable
fun AddressText(text2: String) {
    CardText(
        text1 = "${stringResource(id = R.string.address)}: ",
        color1 = MaterialTheme.colorScheme.primary,
        text2 = text2,
        color2 = MaterialTheme.colorScheme.onPrimary
    )
}

@Composable
fun LatitudeText(text2: String) {
    CardText(
        text1 = "${stringResource(id = R.string.latitude)}: ",
        color1 = MaterialTheme.colorScheme.primary,
        text2 = text2,
        color2 = MaterialTheme.colorScheme.onPrimary
    )
}

@Composable
fun LongitudeText(text2: String) {
    CardText(
        text1 = "${stringResource(id = R.string.longitude)}: ",
        color1 = MaterialTheme.colorScheme.primary,
        text2 = text2,
        color2 = MaterialTheme.colorScheme.onPrimary
    )
}

@Composable
fun TypeText(text2: String) {
    CardText(
        text1 = "${stringResource(id = R.string.type)}: ",
        color1 = MaterialTheme.colorScheme.primary,
        text2 = text2,
        color2 = MaterialTheme.colorScheme.onPrimary
    )
}

@Composable
fun VolumeText(text2: String) {
    CardText(
        text1 = "${stringResource(id = R.string.volume)}: ",
        color1 = MaterialTheme.colorScheme.primary,
        text2 = text2,
        color2 = MaterialTheme.colorScheme.onPrimary
    )
}

@Composable
fun DescriptionText(text2: String) {
    CardText(
        text1 = "${stringResource(id = R.string.description)}: ",
        color1 = MaterialTheme.colorScheme.primary,
        text2 = text2,
        color2 = MaterialTheme.colorScheme.onPrimary
    )
}