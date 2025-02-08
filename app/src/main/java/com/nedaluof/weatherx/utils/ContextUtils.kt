package com.nedaluof.weatherx.utils

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity

/**
 * Created By NedaluOf - 1/18/2025.
 */fun Context.getActivity(): ComponentActivity? = when (this) {
  is ComponentActivity -> this
  is ContextWrapper -> baseContext.getActivity()
  else -> null
}