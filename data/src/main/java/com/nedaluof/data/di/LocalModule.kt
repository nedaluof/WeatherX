package com.nedaluof.data.di

import android.content.Context
import androidx.room.Room
import com.nedaluof.data.data_source.local.database.WeatherXDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created By NedaluOf - 1/30/2025.
 */
@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

  @Singleton
  @Provides
  fun provideWeatherXDatabase(
    @ApplicationContext context: Context
  ): WeatherXDatabase {
    return Room.databaseBuilder(
      context = context,
      klass = WeatherXDatabase::class.java,
      name = "weather_x.db"
    ).build()
  }

  @Singleton
  @Provides
  fun provideCitiesDao(database: WeatherXDatabase) = database.citiesDao
}