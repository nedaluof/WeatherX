package com.nedaluof.domain.usecase.location

import kotlinx.coroutines.CoroutineScope

/**
 * Created By NedaluOf - 1/24/2025.
 */
interface SaveLocationUseCase {
  fun saveLocation(
    scope: CoroutineScope,
    coordinates: Pair<Double, Double>,
    onSaved: () -> Unit
  )
}