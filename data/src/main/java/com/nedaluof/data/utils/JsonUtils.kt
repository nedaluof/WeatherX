package com.nedaluof.data.utils

import android.content.Context
import java.io.IOException

/**
 * Created By NedaluOf - 1/30/2025.
 */
fun Context.loadJsonFileFromAsset(
  fileName: String
): String? {
  return try {
    assets.open(fileName).bufferedReader().use { it.readText() }
  } catch (e: IOException) {
    e.printStackTrace()
    null
  }
}