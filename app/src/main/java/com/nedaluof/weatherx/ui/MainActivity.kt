package com.nedaluof.weatherx.ui

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nedaluof.weatherx.ui.navigation.WeatherXNavigationGraph
import com.nedaluof.weatherx.ui.theme.WeatherXTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge(
      statusBarStyle = SystemBarStyle.dark(Color.parseColor("#28282B"))
    )
    setContent{ WeatherX() }
  }
}

@Composable
fun WeatherX(modifier: Modifier = Modifier) {
  WeatherXTheme {
    Surface(modifier = modifier.fillMaxSize()) {
      WeatherXNavigationGraph()
    }
  }
}