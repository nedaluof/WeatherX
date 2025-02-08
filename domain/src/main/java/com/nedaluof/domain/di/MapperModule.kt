package com.nedaluof.domain.di

import com.nedaluof.data.data_source.local.database.entity.CityEntity
import com.nedaluof.data.data_source.remote.model.GetForecastResponse
import com.nedaluof.domain.model.city.CityModel
import com.nedaluof.domain.model.city.CityModelMapper
import com.nedaluof.domain.model.weather.WeatherModel
import com.nedaluof.domain.model.weather.WeatherModelMapper
import com.nedaluof.domain.model.common.Mapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created By NedaluOf - 1/24/2025.
 */
@InstallIn(SingletonComponent::class)
@Module
abstract class MapperModule {

  @Singleton
  @Binds
  abstract fun bindWeatherModelMapper(
    mapperImpl: WeatherModelMapper
  ): Mapper<GetForecastResponse, WeatherModel>

  @Singleton
  @Binds
  abstract fun bindCityModelMapper(
    mapperImpl: CityModelMapper
  ): Mapper<CityEntity, CityModel>
}