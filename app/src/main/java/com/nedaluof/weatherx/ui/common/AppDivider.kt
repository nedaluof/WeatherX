package com.nedaluof.weatherx.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nedaluof.weatherx.ui.theme.TransparentWhite

/**
 * Created By NedaluOf - 1/28/2025.
 */
@Composable
fun AppDivider(
  modifier: Modifier = Modifier,
  height: Dp = 2.dp
) {
  HorizontalDivider(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp)
      .height(height),
    color = TransparentWhite
  )
}