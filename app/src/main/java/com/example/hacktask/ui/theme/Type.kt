package com.example.hacktask.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.hacktask.R

/**Fonts*/
val PPPangramSansRoundedFont = FontFamily (
    Font(R.font.pp_pangram_sans_rounded_medium, weight = FontWeight.Medium),
    Font(R.font.pp_pangram_sans_rounded_semibold, weight = FontWeight.SemiBold),
    Font(R.font.pp_pangram_sans_rounded_bold, weight = FontWeight.Bold),
)

val InterFont = FontFamily(
    Font(R.font.inter_light, weight = FontWeight.Light),
    Font(R.font.inter_semibold, weight = FontWeight.SemiBold),
)

/**Styles*/
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

)

//Размеры шрифтов берутся из Dimens.kt в зависимости от размера экрана
val Typography.Logo: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.Normal,
        fontSize = MaterialTheme.dimens.logoTextSize,
    )

val Typography.Heading1: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = PPPangramSansRoundedFont,
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.dimens.heading1TextSize,
    )

val Typography.Heading2: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = PPPangramSansRoundedFont,
        fontWeight = FontWeight.Medium,
        fontSize = MaterialTheme.dimens.heading2TextSize,
    )

val Typography.Buttons: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = PPPangramSansRoundedFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = MaterialTheme.dimens.buttonsTextSize,
    )

val Typography.TypingText: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = PPPangramSansRoundedFont,
        fontWeight = FontWeight.Medium,
        fontSize = MaterialTheme.dimens.typingTextSize,
    )

val Typography.Description: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.Light,
        fontSize = MaterialTheme.dimens.descriptionTextSize,
    )

val Typography.TypingText2: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = MaterialTheme.dimens.typingTextSize2,
    )
