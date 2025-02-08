package com.nedaluof.domain.usecase.cities

import com.nedaluof.domain.model.city.CityModel
import kotlinx.coroutines.flow.Flow

/**
 * Created By NedaluOf - 1/30/2025.
 */
interface CitiesUseCase {
  fun loadCities(searchQuery: String): Flow<List<CityModel>>
}