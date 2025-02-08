package com.nedaluof.domain.usecase.weather

import com.nedaluof.domain.model.weather.WeatherModel
import kotlinx.coroutines.CoroutineScope

/**
 * Created By NedaluOf - 1/24/2025.
 */
interface WeatherUseCase {
  fun loadWeatherForecast(
    scope: CoroutineScope,
    onResult: (Result<WeatherModel>) -> Unit
  )
}