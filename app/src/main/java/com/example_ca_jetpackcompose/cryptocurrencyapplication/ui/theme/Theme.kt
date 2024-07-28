package com.example_ca_jetpackcompose.cryptocurrencyapplication.ui.theme

import android.app.Activity
import android.os.Build
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorPalette = darkColorScheme(
    primary = Color.Green,
    background = DarkBlue,
    onPrimary = Color.DarkGray,
    onBackground = TextWhite,
    secondary = Red
)

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun CryptocurrencyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = DarkColorPalette,
        typography = Typography,
        shapes = Shapes(),
        content = content
    )
}

