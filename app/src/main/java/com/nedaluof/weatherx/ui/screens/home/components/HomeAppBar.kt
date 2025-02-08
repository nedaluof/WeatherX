package com.nedaluof.weatherx.ui.screens.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nedaluof.weatherx.R

/**
 * Created By NedaluOf - 1/18/2025.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview(backgroundColor = 0xFF0000FF)
@Composable
fun HomeAppBar(
  modifier: Modifier = Modifier,
  titleText: String = "Amman, Jo",
  currentWeatherUnitIsMetric: Boolean = true,
  onChooseCityClicked: () -> Unit = {},
  onWeatherUnitClicked: () -> Unit = {}
) {
  TopAppBar(
    modifier = modifier
      .fillMaxWidth()
      .padding(top = 16.dp),
    colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.Transparent),
    title = {
      Text(
        text = titleText,
        style = MaterialTheme.typography.headlineLarge
      )
    },
    actions = {
      IconButton(onClick = onWeatherUnitClicked) {
        Icon(
          painter = painterResource(if(currentWeatherUnitIsMetric) R.drawable.ic_celsius else R.drawable.ic_fahrenheit),
          contentDescription = null,
          tint = null
        )
      }
      IconButton(onClick = onChooseCityClicked) {
        Icon(
          painter = painterResource(R.drawable.ic_earth),
          contentDescription = null
        )
      }
    }
  )
}