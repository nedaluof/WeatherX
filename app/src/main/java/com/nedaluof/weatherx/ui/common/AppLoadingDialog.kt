package com.nedaluof.weatherx.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.nedaluof.weatherx.ui.theme.TransparentWhite

/**
 * Created By NedaluOf - 2/1/2025.
 */

@Preview
@Composable
fun AppLoadingDialog(modifier: Modifier = Modifier) {
  Dialog(
    properties = DialogProperties(
      dismissOnBackPress = false,
      dismissOnClickOutside = false
    ),
    onDismissRequest = {}
  ) {
    Surface(
      modifier = modifier.fillMaxWidth()
        .height(150.dp),
      shape = RoundedCornerShape(24.dp),
      color = TransparentWhite
    ) {
      AppLoadingIndicator(modifier = Modifier.size(24.dp))
    }
  }
}