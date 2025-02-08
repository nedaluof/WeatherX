package com.nedaluof.weatherx.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner

/**
 * Created By NedaluOf - 2/3/2025.
 */

@Composable
fun OnResume(
  onResume: () -> Unit
) {
  val lifecycleOwner = LocalLifecycleOwner.current
  val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()
  LaunchedEffect(lifecycleState) {
    when (lifecycleState) {
      Lifecycle.State.RESUMED -> onResume()
      else -> Unit
    }
  }
}