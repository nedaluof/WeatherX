package com.nedaluof.weatherx.ui.screens.home.pick_location.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.nedaluof.weatherx.R

/**
 * Created By NedaluOf - 2/2/2025.
 */
@Preview
@Composable
fun ConfirmSelectedCityDialog(
  modifier: Modifier = Modifier,
  locationName: String = "Jordan / Amman",
  onDismiss: () -> Unit = {},
  onConfirm: () -> Unit = {}
) {
  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(
      usePlatformDefaultWidth = false
    )
  ) {
    Card(
      modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(24.dp),
      shape = RoundedCornerShape(16.dp),
    ) {
      Icon(
        modifier = Modifier
          .size(60.dp)
          .padding(top = 24.dp)
          .align(Alignment.CenterHorizontally),
        imageVector = Icons.Default.MyLocation,
        tint = MaterialTheme.colorScheme.tertiary,
        contentDescription = null
      )
      
      Text(
        text = stringResource(R.string.confirm_selected_city_label, locationName),
        fontSize = 18.sp,
        modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 24.dp)
      )

      Button(
        onClick = {
          onDismiss()
          onConfirm()
        },
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        colors = ButtonDefaults.buttonColors()
          .copy(containerColor = MaterialTheme.colorScheme.tertiary)
      ) {
        Text(
          text = stringResource(R.string.confirm_label),
          color = Color.White,
          fontSize = 14.sp
        )
      }
    }
  }
}