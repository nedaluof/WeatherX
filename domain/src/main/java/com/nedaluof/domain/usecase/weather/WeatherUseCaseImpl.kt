package com.nedaluof.domain.usecase.weather

import com.nedaluof.data.data_source.remote.model.GetForecastResponse
import com.nedaluof.data.repository.forecast.ForecastRepository
import com.nedaluof.data.repository.location.LocationRepository
import com.nedaluof.data.repository.settings.SettingsRepository
import com.nedaluof.domain.model.common.Mapper
import com.nedaluof.domain.model.weather.WeatherModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By NedaluOf - 1/24/2025.
 */
class WeatherUseCaseImpl @Inject constructor(
  private val forecastRepository: ForecastRepository,
  private val locationRepository: LocationRepository,
  private val settingsRepository: SettingsRepository,
  private val mapper: Mapper<GetForecastResponse, WeatherModel>
) : WeatherUseCase {

  //region logic
  override fun loadWeatherForecast(
    scope: CoroutineScope,
    onResult: (Result<WeatherModel>) -> Unit
  ) {
    val locationCoordinates = locationRepository.loadLocation()
    if (locationCoordinates.first != 0.0 && locationCoordinates.second != 0.0) {
      val scaleUnit =
        if (settingsRepository.currentWeatherScaleUnitIsMetric()) "metric" else "imperial"
      scope.launch(Dispatchers.IO) {
        forecastRepository.loadForecast(locationCoordinates, scaleUnit) { result ->
          if (result.isSuccess) {
            result.onSuccess { response ->
              onResult(Result.success(mapper.toModel(response)))
            }
          } else {
            result.onFailure { onResult(Result.failure(it)) }
          }
        }
      }
    } else {
      onResult(Result.failure(Exception("No Location")))
    }
  }
  //endregion
}