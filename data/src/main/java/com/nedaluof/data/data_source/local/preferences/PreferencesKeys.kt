package com.nedaluof.data.data_source.local.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * Created By NedaluOf - 1/24/2025.
 */
object PreferencesKeys {
  val COLLECTED_LOCATION_STRING = stringPreferencesKey("collected_location_string")
  val CURRENT_WEATHER_SCALE_UNIT_IS_METRIC = booleanPreferencesKey("current_weather_scale_unit_is_metric")
}