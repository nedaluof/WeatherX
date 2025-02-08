package com.nedaluof.weatherx.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nedaluof.domain.usecase.settings.SettingsUseCase
import com.nedaluof.domain.usecase.weather.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Created By NedaluOf - 1/17/2025.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
  private val weatherUseCase: WeatherUseCase,
  private val settingsUseCase: SettingsUseCase
) : ViewModel() {

  //region ui variables
  private val _weatherUiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)
  val weatherUiState = _weatherUiState.asStateFlow()
  //endregion

  //region logic
  fun reloadWeatherData(){
    loadWeatherData()
  }

  private fun loadWeatherData() {
    _weatherUiState.value = WeatherUiState.Loading
    weatherUseCase.loadWeatherForecast(viewModelScope) { result ->
      result.onSuccess { weatherModel ->
        _weatherUiState.value = WeatherUiState.WeatherData(weatherModel)
      }.onFailure { exception ->
        exception.message?.let { message ->
          _weatherUiState.value =
            if (message != "No Location") WeatherUiState.Error(message) else WeatherUiState.PickLocation
        }
      }
    }
  }

  fun currentWeatherScaleUnitIsMetric() = settingsUseCase.currentWeatherScaleUnitIsMetric()

  fun updateWeatherScaleUnit(isMetric: Boolean) {
    settingsUseCase.updateCurrentWeatherScaleUnit(viewModelScope, isMetric) {
      loadWeatherData()
    }
  }
  //endregion

  init {
    loadWeatherData()
  }
}