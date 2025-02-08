package com.nedaluof.data.repository.cities

import com.nedaluof.data.data_source.local.database.entity.CityEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created By NedaluOf - 1/30/2025.
 */
interface CitiesRepository {
  fun loadCities(
    searchQuery: String
  ): Flow<List<CityEntity>>
}