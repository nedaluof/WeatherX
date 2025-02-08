package com.nedaluof.data.repository.location

import com.nedaluof.data.data_source.local.preferences.PreferencesKeys
import com.nedaluof.data.data_source.local.preferences.PreferencesManager
import javax.inject.Inject

/**
 * Created By NedaluOf - 1/24/2025.
 */
class LocationRepositoryImpl @Inject constructor(
  private val preferences: PreferencesManager
) : LocationRepository {

  //region logic
  override suspend fun saveLocation(
    coordinates: Pair<Double, Double>,
    onSaved: () -> Unit
  ) {
    preferences.putString(
      PreferencesKeys.COLLECTED_LOCATION_STRING,
      "${coordinates.first},${coordinates.second}"
    )
    onSaved()
  }

  override fun loadLocation(): Pair<Double, Double> {
    val location = preferences.getString(PreferencesKeys.COLLECTED_LOCATION_STRING)
    if (location.isNotEmpty()) {
      val coordinates = location.split(",")
      val latitude = coordinates[0].toDouble()
      val longitude = coordinates[1].toDouble()
      return Pair(latitude, longitude)
    }
    return Pair(0.0, 0.0)
  }
  //endregion
}