package com.nedaluof.weatherx.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Created By NedaluOf - 1/19/2025.
 */

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
  modifier: Modifier = Modifier,
  onBackClicked : () -> Unit = {}
) {
  TopAppBar(
    title = {},
    navigationIcon = {
      IconButton(
        onClick = onBackClicked
      ) {
        Icon(
          imageVector = Icons.Default.ArrowBackIosNew,
          contentDescription = null
        )
      }
    }
  )
}