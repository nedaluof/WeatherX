package com.nedaluof.domain.usecase.settings

import com.nedaluof.data.repository.settings.SettingsRepository
import com.nedaluof.domain.utils.post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By NedaluOf - 2/1/2025.
 */
class SettingsUseCaseImpl @Inject constructor(
  private val settingsRepository: SettingsRepository
) : SettingsUseCase {

  //region logic
  override fun currentWeatherScaleUnitIsMetric(): Boolean {
    return settingsRepository.currentWeatherScaleUnitIsMetric()
  }

  override fun updateCurrentWeatherScaleUnit(
    scope: CoroutineScope,
    isMetric: Boolean,
    onUpdated: () -> Unit
  ) {
    scope.launch {
      settingsRepository.updateCurrentWeatherScaleUnit(isMetric) {
        scope.post(onUpdated)
      }
    }
  }
  //endregion
}