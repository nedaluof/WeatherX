package com.nedaluof.data.repository.settings

import com.nedaluof.data.data_source.local.preferences.PreferencesKeys
import com.nedaluof.data.data_source.local.preferences.PreferencesManager
import javax.inject.Inject

/**
 * Created By NedaluOf - 2/1/2025.
 */
class SettingsRepositoryImpl @Inject constructor(
  private val preferences: PreferencesManager
) : SettingsRepository {

  //region logic
  override fun currentWeatherScaleUnitIsMetric(): Boolean {
    return preferences.getBoolean(PreferencesKeys.CURRENT_WEATHER_SCALE_UNIT_IS_METRIC, true)
  }

  override suspend fun updateCurrentWeatherScaleUnit(
    isMetric: Boolean, onUpdated: () -> Unit
  ) {
    preferences.putBoolean(PreferencesKeys.CURRENT_WEATHER_SCALE_UNIT_IS_METRIC, isMetric)
    onUpdated()
  }
  //endregion
}