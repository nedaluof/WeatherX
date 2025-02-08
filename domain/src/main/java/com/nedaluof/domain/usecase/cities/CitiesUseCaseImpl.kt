package com.nedaluof.domain.usecase.cities

import com.nedaluof.data.data_source.local.database.entity.CityEntity
import com.nedaluof.data.repository.cities.CitiesRepository
import com.nedaluof.domain.model.city.CityModel
import com.nedaluof.domain.model.common.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created By NedaluOf - 1/30/2025.
 */
class CitiesUseCaseImpl @Inject constructor(
  private val repository: CitiesRepository,
  private val mapper: Mapper<CityEntity, CityModel>
) : CitiesUseCase {

  //region logic
  override fun loadCities(searchQuery: String): Flow<List<CityModel>> =
    repository.loadCities(searchQuery).map(mapper::toList)
  //endregion
}