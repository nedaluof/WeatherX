package com.nedaluof.data.data_source.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nedaluof.data.data_source.local.database.entity.CityEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created By NedaluOf - 1/30/2025.
 */
@Dao
interface CitiesDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertCities(cityEntity: List<CityEntity>)

  @Query("SELECT (SELECT COUNT(*) FROM cities) == 0")
  suspend fun isCitiesEmpty(): Boolean

  @Query("SELECT * FROM cities")
  fun loadCities(): Flow<List<CityEntity>>

  @Query(
    """
        SELECT * FROM cities 
        WHERE (city LIKE '%' || :searchQuery || '%' OR country LIKE '%' || :searchQuery || '%') 
        COLLATE NOCASE
    """
  )
  fun searchInCities(searchQuery: String): Flow<List<CityEntity>>
}