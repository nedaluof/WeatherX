package com.nedaluof.data.data_source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nedaluof.data.data_source.local.database.dao.CitiesDao
import com.nedaluof.data.data_source.local.database.entity.CityEntity

/**
 * Created By NedaluOf - 1/30/2025.
 */
@Database(entities = [CityEntity::class], version = 1, exportSchema = false)
abstract class WeatherXDatabase : RoomDatabase() {
  //region dao
  abstract val citiesDao: CitiesDao
  //endregion
}