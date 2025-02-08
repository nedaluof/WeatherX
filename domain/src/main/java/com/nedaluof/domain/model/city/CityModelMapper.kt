package com.nedaluof.domain.model.city

import com.nedaluof.data.data_source.local.database.entity.CityEntity
import com.nedaluof.domain.model.common.Mapper
import javax.inject.Inject

/**
 * Created By NedaluOf - 1/24/2025.
 */
class CityModelMapper @Inject constructor() : Mapper<CityEntity, CityModel> {

  //region logic
  override fun toModel(input: CityEntity) = CityModel(
    cityName = input.city,
    countryName = input.country,
    coordinates = Pair(input.latitude, input.longitude)
  )
  //endregion
}