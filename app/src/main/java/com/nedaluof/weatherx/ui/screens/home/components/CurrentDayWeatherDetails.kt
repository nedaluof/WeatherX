package com.nedaluof.weatherx.ui.screens.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nedaluof.domain.model.weather.WeatherDataModel
import com.nedaluof.weatherx.R
import com.nedaluof.weatherx.ui.theme.TransparentWhite
import com.nedaluof.weatherx.ui.theme.Typography

/**
 * Created By NedaluOf - 1/25/2025.
 */
@Preview
@Composable
fun CurrentDayWeatherDetails(
  modifier: Modifier = Modifier,
  weatherData: WeatherDataModel = WeatherDataModel.fake(),
  sunriseTime: String = "06:00 AM",
  sunsetTime: String = "07:00 PM"
) {
  var isExpanded by remember { mutableStateOf(false) }
  Surface(
    modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp),
    color = Color.Transparent,
    shape = RoundedCornerShape(CornerSize(16.dp)),
    border = BorderStroke(width = 1.dp, color = TransparentWhite)
  ) {
    Column(modifier = modifier.fillMaxWidth()) {
      Row(
        modifier = Modifier
          .padding(start = 16.dp, end = 4.dp)
          .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          modifier = Modifier,
          text = "${weatherData.temperature}°",
          style = Typography.headlineLarge.copy(color = Color.White, fontSize = 120.sp)
        )
        Column(
          modifier = Modifier
            .align(Alignment.Bottom)
            .padding(start = 8.dp, bottom = 16.dp)
            .weight(1f),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
        ) {
          AsyncImage(
            modifier = Modifier.size(100.dp),
            model = ImageRequest.Builder(LocalContext.current).data(weatherData.iconUrl)
              .crossfade(true).placeholder(R.drawable.ic_test_sun).build(),
            contentDescription = null,
            contentScale = ContentScale.Fit
          )
          Text(
            modifier = Modifier.padding(top = 2.dp),
            text = weatherData.weatherStatus,
            style = Typography.titleLarge.copy(color = Color.White)
          )
          Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "${weatherData.maxTemperature}° / ${weatherData.minTemperature}°",
            style = Typography.labelMedium.copy(
              color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Light
            )
          )
        }
      }

      MoreDetailsWidget(modifier = Modifier,
        isExpanded = isExpanded,
        onExpandClicked = { isExpanded = !isExpanded })

      AnimatedVisibility(
        visible = isExpanded
      ) {
        WeatherDetails(weatherData = weatherData)
      }
    }
  }
}

@Composable
fun MoreDetailsWidget(
  modifier: Modifier = Modifier,
  infiniteTransition: InfiniteTransition = rememberInfiniteTransition(),
  isExpanded: Boolean = false,
  onExpandClicked: () -> Unit
) {
  val position by infiniteTransition.animateFloat(
    initialValue = -5f,
    targetValue = 5f,
    animationSpec = infiniteRepeatable(tween(800), RepeatMode.Reverse)
  )
  Row(
    modifier = modifier
      .padding(start = 16.dp)
      .padding(vertical = 16.dp)
      .clickable(onClick = onExpandClicked), verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      modifier = Modifier,
      text = stringResource(if (isExpanded) R.string.close_details_label else R.string.open_details_label),
      color = if (isExpanded) TransparentWhite else Color.White,
      fontSize = 14.sp,
      fontWeight = FontWeight.Light
    )
    Icon(
      modifier = Modifier
        .size(30.dp)
        .padding(start = 8.dp)
        .offset(y = position.dp),
      imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
      tint = if (isExpanded) TransparentWhite else Color.White,
      contentDescription = null
    )
  }
}