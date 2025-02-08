package com.nedaluof.weatherx.ui.screens.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nedaluof.domain.model.weather.WeatherDataModel
import com.nedaluof.weatherx.R
import com.nedaluof.weatherx.ui.common.AppDivider

/**
 * Created By NedaluOf - 1/25/2025.
 */
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun WeatherDetails(
  modifier: Modifier = Modifier,
  weatherData: WeatherDataModel = WeatherDataModel.fake(),
  sunriseTime: String = "06:00 AM",
  sunsetTime: String = "07:00 PM"
) {
  Surface(
    modifier = modifier.fillMaxWidth(),
    shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
    color = MaterialTheme.colorScheme.tertiary
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
    ) {
      WeatherDetailsItem(
        icon = R.drawable.ic_sunrise,
        label = R.string.sunrise_label,
        value = sunriseTime
      )
      AppDivider()
      WeatherDetailsItem(
        icon = R.drawable.ic_sunset,
        label = R.string.sunset_label,
        value = sunsetTime
      )
      AppDivider()
      WeatherDetailsItem(
        icon = R.drawable.ic_feels_like,
        label = R.string.feels_like_label,
        value = "${weatherData.feelsLike}°"
      )
      AppDivider()
      WeatherDetailsItem(
        icon = R.drawable.ic_humidity,
        label = R.string.humidity_label,
        value = "${weatherData.humidity}%"
      )
      AppDivider()
      WeatherDetailsItem(
        icon = R.drawable.ic_pressure,
        label = R.string.pressure_label,
        value = weatherData.pressure
      )
    }
  }
}