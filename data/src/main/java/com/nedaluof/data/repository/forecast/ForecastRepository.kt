package com.nedaluof.data.repository.forecast

import com.nedaluof.data.data_source.remote.model.GetForecastResponse

/**
 * Created By NedaluOf - 1/17/2025.
 */
interface ForecastRepository {
  suspend fun loadForecast(
    locationCoordinates: Pair<Double, Double>,
    scaleUnit: String,
    onResult: (Result<GetForecastResponse>) -> Unit
  )
}