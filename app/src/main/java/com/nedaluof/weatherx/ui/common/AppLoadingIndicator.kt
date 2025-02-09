package com.nedaluof.weatherx.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Created By NedaluOf - 1/18/2025.
 */
@Preview
@Composable
fun AppLoadingIndicator(modifier: Modifier = Modifier) {
  Box(modifier = modifier) {
    CircularProgressIndicator(
      modifier = Modifier.align(Alignment.Center)
    )
  }
}