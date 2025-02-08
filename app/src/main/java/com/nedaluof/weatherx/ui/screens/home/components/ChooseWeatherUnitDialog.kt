package com.nedaluof.weatherx.ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.nedaluof.weatherx.R

/**
 * Created By NedaluOf - 2/1/2025.
 */
@Preview
@Composable
fun ChooseWeatherUnitDialog(
  modifier: Modifier = Modifier,
  lastSelectionIsMetric: Boolean = true,
  onDismiss: () -> Unit = {},
  onConfirm: (isMetric: Boolean) -> Unit = {}
) {
  val radioOptions = listOf(
    R.string.weather_unit_metric_label,
    R.string.weather_unit_imperial_label
  )
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
      Text(
        text = stringResource(R.string.choose_your_scale_label),
        fontSize = 18.sp,
        modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 24.dp)
      )
      val (selectedOption, onOptionSelected) = remember { mutableIntStateOf(if (lastSelectionIsMetric) 0 else 1) }
      Column(
        modifier = Modifier
          .selectableGroup()
          .fillMaxWidth()
          .padding(top = 16.dp)
      ) {
        radioOptions.forEachIndexed { index, textResourceId ->
          Row(
            Modifier
              .fillMaxWidth()
              .height(45.dp)
              .selectable(
                selected = index == selectedOption,
                onClick = { onOptionSelected(index) },
                role = Role.RadioButton
              )
              .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            RadioButton(
              selected = index == selectedOption,
              colors = RadioButtonDefaults.colors().copy(
                selectedColor = MaterialTheme.colorScheme.tertiary,
                unselectedColor = MaterialTheme.colorScheme.tertiary,
              ),
              onClick = null
            )
            Text(
              text = stringResource(radioOptions[index]),
              fontSize = 14.sp,
              modifier = Modifier.padding(start = 8.dp)
            )
          }
        }
      }

      Button(
        onClick = {
          onConfirm(selectedOption == 0)
          onDismiss()
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