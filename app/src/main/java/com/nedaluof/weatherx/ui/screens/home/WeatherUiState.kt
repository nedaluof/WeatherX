package com.nedaluof.weatherx.ui.screens.home

import com.nedaluof.domain.model.weather.WeatherModel

/**
 * Created By NedaluOf - 1/18/2025.
 */
sealed class WeatherUiState {
  data object Idle : WeatherUiState()
  data object Loading : WeatherUiState()
  data object PickLocation : WeatherUiState()
  data class Error(val errorMessage: String) : WeatherUiState()
  data class WeatherData(val data: WeatherModel?) : WeatherUiState()
}