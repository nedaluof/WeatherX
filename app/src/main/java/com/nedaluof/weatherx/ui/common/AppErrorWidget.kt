package com.nedaluof.weatherx.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nedaluof.weatherx.ui.theme.PurpleGrey40

/**
 * Created By NedaluOf - 1/18/2025.
 */
@Preview
@Composable
fun AppErrorWidget(
  modifier: Modifier = Modifier,
  message: String = "Something went wrong"
) {
  Box(modifier = Modifier.fillMaxSize()) {
    Surface(
      modifier = Modifier
        .align(Alignment.Center)
        .fillMaxWidth()
        .padding(24.dp),
      color = PurpleGrey40,
      shape = RoundedCornerShape(CornerSize(16.dp))
    ) {
      Text(
        modifier = Modifier.padding(16.dp),
        text = message,
        textAlign = TextAlign.Center,
        fontSize = 12.sp,
        letterSpacing = 2.sp,
        color = Color.White
      )
    }
  }
}