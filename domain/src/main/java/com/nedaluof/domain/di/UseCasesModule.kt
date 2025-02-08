package com.nedaluof.domain.di

import com.nedaluof.domain.usecase.cities.CitiesUseCase
import com.nedaluof.domain.usecase.cities.CitiesUseCaseImpl
import com.nedaluof.domain.usecase.location.SaveLocationUseCase
import com.nedaluof.domain.usecase.location.SaveLocationUseCaseImpl
import com.nedaluof.domain.usecase.settings.SettingsUseCase
import com.nedaluof.domain.usecase.settings.SettingsUseCaseImpl
import com.nedaluof.domain.usecase.weather.WeatherUseCase
import com.nedaluof.domain.usecase.weather.WeatherUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created By NedaluOf - 1/24/2025.
 */
@InstallIn(ViewModelComponent::class)
@Module
abstract class UseCasesModule {

  @ViewModelScoped
  @Binds
  abstract fun bindSaveLocationUseCase(
    useCaseImpl: SaveLocationUseCaseImpl
  ): SaveLocationUseCase

  @ViewModelScoped
  @Binds
  abstract fun bindHomeUseCase(
    useCaseImpl: WeatherUseCaseImpl
  ): WeatherUseCase

  @ViewModelScoped
  @Binds
  abstract fun bindCitiesUseCase(
    useCaseImpl: CitiesUseCaseImpl
  ): CitiesUseCase

  @ViewModelScoped
  @Binds
  abstract fun bindSettingsUseCase(
    useCaseImpl: SettingsUseCaseImpl
  ): SettingsUseCase
}