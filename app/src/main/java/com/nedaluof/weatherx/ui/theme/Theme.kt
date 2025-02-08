package com.nedaluof.weatherx.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

@Composable
fun WeatherXTheme(content: @Composable () -> Unit) {
  MaterialTheme(
    colorScheme = darkColorScheme(
      primary = Pink80,
      secondary = PurpleGrey40,
      tertiary = Pink40,
      background = MateBlack,
      surface = MateBlack,
      onBackground = Pink80
    ),
    typography = Typography,
    content = content
  )
}