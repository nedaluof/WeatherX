package com.nedaluof.data.di

import com.nedaluof.data.repository.cities.CitiesRepository
import com.nedaluof.data.repository.cities.CitiesRepositoryImpl
import com.nedaluof.data.repository.forecast.ForecastRepository
import com.nedaluof.data.repository.forecast.ForecastRepositoryImpl
import com.nedaluof.data.repository.location.LocationRepository
import com.nedaluof.data.repository.location.LocationRepositoryImpl
import com.nedaluof.data.repository.settings.SettingsRepository
import com.nedaluof.data.repository.settings.SettingsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created By NedaluOf - 1/17/2025.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

  @Singleton
  @Binds
  abstract fun bindLocationRepository(
    repositoryImpl: LocationRepositoryImpl
  ): LocationRepository

  @Singleton
  @Binds
  abstract fun bindForecastRepository(
    repositoryImpl: ForecastRepositoryImpl
  ): ForecastRepository

  @Singleton
  @Binds
  abstract fun bindCitiesRepository(
    repositoryImpl: CitiesRepositoryImpl
  ): CitiesRepository

  @Singleton
  @Binds
  abstract fun bindSettingsRepository(
    repositoryImpl: SettingsRepositoryImpl
  ): SettingsRepository
}