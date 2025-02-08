package com.nedaluof.data.repository.forecast

import com.nedaluof.data.BuildConfig
import com.nedaluof.data.data_source.remote.api.WeatherXApiService
import com.nedaluof.data.data_source.remote.model.GetForecastResponse
import javax.inject.Inject

/**
 * Created By NedaluOf - 1/17/2025.
 */
class ForecastRepositoryImpl @Inject constructor(
  private val apiService: WeatherXApiService
) : ForecastRepository {

  //region logic
  override suspend fun loadForecast(
    locationCoordinates: Pair<Double, Double>,
    scaleUnit: String,
    onResult: (Result<GetForecastResponse>) -> Unit
  ) {
    try {
      val latitude = locationCoordinates.first
      val longitude = locationCoordinates.second
      val response = apiService.requestForecastData(
        latitude = latitude,
        longitude = longitude,
        units = scaleUnit,
        apiKey = BuildConfig.API_KEY
      )
      if (response.isSuccessful && response.code() in 200..299) {
        response.body()?.let { forecastResponse ->
          onResult(Result.success(forecastResponse))
        } ?: run {
          onResult(Result.failure(Exception(response.errorBody()?.toString() ?: "")))
        }
      } else {
        onResult(Result.failure(Exception("Something went wrong!")))
      }
    } catch (e: Exception) {
      onResult(Result.failure(e))
      e.printStackTrace()
    }
  }
  //endregion
}