package com.nedaluof.weatherx.ui.screens.home.pick_location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nedaluof.domain.model.city.CityModel
import com.nedaluof.domain.usecase.cities.CitiesUseCase
import com.nedaluof.domain.usecase.location.SaveLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By NedaluOf - 1/30/2025.
 */
@HiltViewModel
class PickLocationViewModel @Inject constructor(
  private val citiesUseCase: CitiesUseCase,
  private val saveLocationUseCase: SaveLocationUseCase
) : ViewModel() {

  //region logic
  //fun loadCities(searchQuery: String) = citiesUseCase.loadCities(searchQuery)

  private val _citiesUiState = MutableStateFlow<CitiesUiState>(CitiesUiState.Idle)
  val citiesUiState = _citiesUiState.asStateFlow()

  fun loadCities(searchQuery: String) {
    viewModelScope.launch {
      citiesUseCase.loadCities(searchQuery).collectLatest { cities ->
        if (cities.isEmpty()) {
          _citiesUiState.value = if (searchQuery.isNotEmpty()) CitiesUiState.CityNotFound else CitiesUiState.Loading
        } else {
          _citiesUiState.value = CitiesUiState.Cities(cities)
        }
      }
    }
  }

  fun savePickedLocation(
    coordinates: Pair<Double, Double>,
    onDone: () -> Unit
  ) {
    saveLocationUseCase.saveLocation(
      viewModelScope,
      coordinates,
      onDone
    )
  }
  //endregion
}

sealed class CitiesUiState {
  data object Idle : CitiesUiState()
  data object Loading : CitiesUiState()
  data object CityNotFound : CitiesUiState()
  data class Cities(val data: List<CityModel>) : CitiesUiState()
}