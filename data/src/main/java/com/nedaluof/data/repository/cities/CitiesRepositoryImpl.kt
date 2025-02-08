package com.nedaluof.data.repository.cities

import android.content.Context
import com.nedaluof.data.data_source.local.database.dao.CitiesDao
import com.nedaluof.data.data_source.local.database.entity.CityEntity
import com.nedaluof.data.data_source.local.database.entity.jsonStringToCities
import com.nedaluof.data.utils.loadJsonFileFromAsset
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By NedaluOf - 1/30/2025.
 */
class CitiesRepositoryImpl @Inject constructor(
  @ApplicationContext private val context: Context,
  private val citiesDao: CitiesDao
) : CitiesRepository {

  //region logic
  override fun loadCities(
    searchQuery: String
  ): Flow<List<CityEntity>> {
    return if (searchQuery.isEmpty()) citiesDao.loadCities() else citiesDao.searchInCities(searchQuery)
  }

  private fun checkCitiesAreFilled() {
    CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
      if (citiesDao.isCitiesEmpty()) {
        context.loadJsonFileFromAsset("cities.json")?.let { cities ->
          citiesDao.insertCities(jsonStringToCities(cities))
        }
      }
    }
  }

  //endregion

  init {
    checkCitiesAreFilled()
  }
}