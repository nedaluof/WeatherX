package com.nedaluof.weatherx.ui.screens.home.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nedaluof.weatherx.R
import com.nedaluof.weatherx.ui.theme.Typography

/**
 * Created By NedaluOf - 1/28/2025.
 */
@Preview
@Composable
fun WeatherDetailsItem(
  modifier: Modifier = Modifier,
  @DrawableRes icon: Int = R.drawable.ic_feels_like,
  @StringRes label: Int = R.string.feels_like_label,
  value: String = "15°"
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 4.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Image(
      modifier = Modifier
        .padding(vertical = 4.dp, horizontal = 8.dp)
        .size(20.dp),
      painter = painterResource(icon),
      contentDescription = null,
      contentScale = ContentScale.FillBounds
    )

    Text(
      modifier = Modifier
        .padding(start = 8.dp)
        .weight(1f),
      text = stringResource(label),
      style = Typography.labelMedium.copy(
        color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Light
      )
    )

    Text(
      modifier = Modifier.padding(end = 8.dp),
      text = value,
      style = Typography.labelMedium.copy(
        color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Light
      )
    )
  }
}