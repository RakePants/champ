package com.example.hacktask.ui.theme

import androidx.compose.ui.graphics.Color

data class PapperColors(
    val primaryBackgroundColor: Color,
    val secondaryBackgroundColor: Color,
    val primaryTextColor: Color,
    val hintColor: Color,
    val errorColor: Color,
    val primaryButtonColor: Color,
    val disabledButtonColor: Color,
    val disabledTextColor: Color,
)

val darkPallet = PapperColors(
    primaryBackgroundColor = Color(0xFF292929),
    secondaryBackgroundColor = Color(0xFF232323),
    primaryTextColor = Color(0xFFFFFFFF),
    hintColor = Color(0xFFAAAAAA),
    errorColor = Color(0xFFE63D43),
    primaryButtonColor = Color(0xFFFFA500),
    disabledButtonColor = Color(0xFF7E7E7E),
    disabledTextColor = Color(0xFF555555)
)

//TODO добавить светлую тему
val lightPallet = PapperColors(
    primaryBackgroundColor = Color(0xFF292929),
    secondaryBackgroundColor = Color(0xFF232323),
    primaryTextColor = Color(0xFFFFFFFF),
    hintColor = Color(0xFFAAAAAA),
    errorColor = Color(0xFFE63D43),
    primaryButtonColor = Color(0xFFFFA500),
    disabledButtonColor = Color(0xFF7E7E7E),
    disabledTextColor = Color(0xFF555555)
)