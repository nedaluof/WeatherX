package com.nedaluof.domain.usecase.settings

import kotlinx.coroutines.CoroutineScope

/**
 * Created By NedaluOf - 2/1/2025.
 */
interface SettingsUseCase {
  fun currentWeatherScaleUnitIsMetric(): Boolean
  fun updateCurrentWeatherScaleUnit(
    scope: CoroutineScope,
    isMetric: Boolean,
    onUpdated: () -> Unit
  )
}