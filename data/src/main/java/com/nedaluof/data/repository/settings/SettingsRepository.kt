package com.nedaluof.data.repository.settings

/**
 * Created By NedaluOf - 2/1/2025.
 */
interface SettingsRepository {

  fun currentWeatherScaleUnitIsMetric(): Boolean

  suspend fun updateCurrentWeatherScaleUnit(
    isMetric: Boolean,
    onUpdated: () -> Unit
  )
}