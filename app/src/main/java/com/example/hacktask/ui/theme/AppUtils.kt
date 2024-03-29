package com.example.hacktask.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember

@Composable
fun AppUtils(
    appDimens: Dimens,
    content: @Composable () -> Unit,
) {
    val appDimens = remember {
        appDimens
    }

    CompositionLocalProvider(value = localAppDimens provides appDimens) {

    }
}

val localAppDimens = compositionLocalOf {
    CompactDimens
}