package com.nedaluof.domain.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created By NedaluOf - 1/24/2025.
 */
fun CoroutineScope.post(block: () -> Unit) {
  this.launch {
    withContext(Dispatchers.Main) {
      block()
    }
  }
}