package com.nedaluof.weatherx.ui.screens.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nedaluof.domain.model.weather.WeatherDataModel
import com.nedaluof.weatherx.R
import com.nedaluof.weatherx.ui.common.AppDivider

/**
 * Created By NedaluOf - 1/25/2025.
 */
@Preview
@Composable
fun WeekWeatherDetails(
  modifier: Modifier = Modifier,
  days: List<WeatherDataModel> = listOf(
    WeatherDataModel.fake(),
    WeatherDataModel.fake()
  )
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp)
      .padding(bottom = 50.dp),
    shape = RoundedCornerShape(CornerSize(16.dp)),
    color = Color.Transparent,
    border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.tertiary)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp),
    ) {
      days.forEach { weatherModel ->
        WeekWeatherItem(weatherModel = weatherModel)
      }
    }
  }
}

@Composable
fun WeekWeatherItem(
  modifier: Modifier = Modifier,
  weatherModel: WeatherDataModel = WeatherDataModel.fake()
) {
  var isExpanded by remember { mutableStateOf(false) }
  Column(modifier = modifier) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .clickable { isExpanded = !isExpanded },
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        modifier = Modifier,
        text = weatherModel.dayName,
        color = if (isExpanded) MaterialTheme.colorScheme.tertiary else Color.White,
        fontSize = 16.sp,
        letterSpacing = 1.3.sp,
        fontWeight = if (isExpanded) FontWeight.Bold else FontWeight.Light
      )
      AsyncImage(
        modifier = Modifier
          .padding(horizontal = 8.dp)
          .size(30.dp),
        model = ImageRequest.Builder(LocalContext.current)
          .data(weatherModel.iconUrl)
          .crossfade(true)
          .placeholder(R.drawable.ic_test_sun)
          .build(),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
      )

      Text(
        modifier = Modifier
          .weight(1f)
          .padding(end = 24.dp),
        text = "${weatherModel.maxTemperature}° / ${weatherModel.minTemperature}°",
        color = if (isExpanded) MaterialTheme.colorScheme.tertiary else Color.White,
        fontSize = 16.sp,
        textAlign = TextAlign.End,
        fontWeight = if (isExpanded) FontWeight.Bold else FontWeight.Light
      )

      Icon(
        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
        tint = if (isExpanded) MaterialTheme.colorScheme.tertiary else Color.White,
        contentDescription = null
      )
    }

    AnimatedVisibility(visible = isExpanded) {
      AppDivider()
      Column(modifier = Modifier.padding(top = 3.dp)) {
        WeatherDetailsItem(
          icon = R.drawable.ic_feels_like,
          label = R.string.feels_like_label,
          value = "${weatherModel.feelsLike}°"
        )
        WeatherDetailsItem(
          icon = R.drawable.ic_humidity,
          label = R.string.humidity_label,
          value = "${weatherModel.humidity}%"
        )
        WeatherDetailsItem(
          icon = R.drawable.ic_pressure,
          label = R.string.pressure_label,
          value = weatherModel.pressure
        )
      }
    }
  }
}