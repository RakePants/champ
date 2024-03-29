package com.example.hacktask.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Dimens(
    val logoTextSize: TextUnit = 96.sp,
    val heading1TextSize: TextUnit = 24.sp,
    val heading2TextSize: TextUnit = 18.sp,
    val buttonsTextSize: TextUnit = 14.sp,
    val typingTextSize: TextUnit = 14.sp,
    val descriptionTextSize: TextUnit = 12.sp,
    val typingTextSize2: TextUnit = 32.sp,

    val gapBetweenComponents1: Dp = 0.dp, //5
    val gapBetweenComponents2: Dp = 0.dp, //10
    val gapBetweenComponents3: Dp = 0.dp, //20
    val gapBetweenComponents4: Dp = 0.dp, //30
    val gapBetweenComponentScreen: Dp = 0.dp, //20

    val buttonsHeight: Dp = 0.dp, //55
    val buttonsWidth: Dp = 0.dp, //1
    val buttonGap: Dp = 0.dp, //10
    val bottomGap: Dp = 0.dp, //5
    val bottomGap2: Dp = 0.dp, //15
    val bottomGap3: Dp = 0.dp, //30

    val cardCornerRadius: Dp = 30.dp,
    val buttonCornerRadius: Dp = 20.dp,
    val textFieldCornerRadius: Dp = 16.dp,
)

val CompactSmallDimens = Dimens(
    gapBetweenComponents1 = 5.dp,
    gapBetweenComponents2 = 10.dp,
    gapBetweenComponents3 = 20.dp,
    gapBetweenComponents4 = 30.dp,
    gapBetweenComponentScreen = 20.dp,
    buttonsHeight = 55.dp,
    buttonsWidth = 1.dp,
    buttonGap = 10.dp,
    bottomGap = 5.dp,
    bottomGap2 = 15.dp,
    bottomGap3 = 30.dp,
)

val CompactMediumDimens = Dimens(
    gapBetweenComponents1 = 5.dp,
    gapBetweenComponents2 = 10.dp,
    gapBetweenComponents3 = 20.dp,
    gapBetweenComponents4 = 30.dp,
    gapBetweenComponentScreen = 20.dp,
    buttonsHeight = 55.dp,
    buttonsWidth = 1.dp,
    buttonGap = 10.dp,
    bottomGap = 5.dp,
    bottomGap2 = 15.dp,
    bottomGap3 = 30.dp,
)
val CompactDimens = Dimens(
    gapBetweenComponents1 = 5.dp,
    gapBetweenComponents2 = 10.dp,
    gapBetweenComponents3 = 20.dp,
    gapBetweenComponents4 = 30.dp,
    gapBetweenComponentScreen = 20.dp,
    buttonsHeight = 55.dp,
    buttonsWidth = 1.dp,
    buttonGap = 10.dp,
    bottomGap = 5.dp,
    bottomGap2 = 15.dp,
    bottomGap3 = 30.dp,
)