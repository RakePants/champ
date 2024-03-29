package com.example.hacktask.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.hacktask.BaseActivity

// цвета для схемы беруться из Color.kt
private val DarkColorScheme = darkColorScheme(
    background = darkPallet.primaryBackgroundColor, //задний фон
    onBackground = darkPallet.secondaryBackgroundColor, //задний фон
    primary = darkPallet.primaryButtonColor, //для кнопок и чата
    onPrimary = darkPallet.primaryTextColor, //для текста и textField`ов
    onPrimaryContainer = darkPallet.hintColor, //для hint текста
    secondaryContainer = darkPallet.disabledButtonColor, //для недействующих кнопок
    onSecondaryContainer = darkPallet.disabledTextColor, //для недействующего текста
    error = darkPallet.errorColor, //для ошибок
)

private val LightColorScheme = lightColorScheme(
    background = darkPallet.primaryBackgroundColor, //задний фон
    onBackground = darkPallet.secondaryBackgroundColor, //задний фон
    primary = darkPallet.primaryButtonColor, //для кнопок и чата
    onPrimary = darkPallet.primaryTextColor, //для текста и textField`ов
    onPrimaryContainer = darkPallet.hintColor, //для hint текста
    secondaryContainer = darkPallet.disabledButtonColor, //для недействующих кнопок
    onSecondaryContainer = darkPallet.disabledTextColor, //для недействующего текста
    error = darkPallet.errorColor, //для ошибок
)

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HacktaskTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    activity: Activity = LocalContext.current as BaseActivity,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    val window = calculateWindowSizeClass(activity = activity)
    val config = LocalConfiguration.current

    var appDimens = CompactDimens

    when (window.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            if (config.screenWidthDp <= 360) {
                appDimens = CompactSmallDimens
            }
            else if (config.screenWidthDp <= 599) {
                appDimens = CompactMediumDimens
            }
            else {
                appDimens = CompactDimens
            }
        }
//        WindowWidthSizeClass.Medium -> {
//
//        }
//        WindowWidthSizeClass.Expanded -> {
//
//        }
    }

    AppUtils(appDimens = appDimens) {}
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

val MaterialTheme.dimens
    @Composable
    get() = localAppDimens.current