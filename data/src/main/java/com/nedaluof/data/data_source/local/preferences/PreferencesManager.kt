package com.nedaluof.data.data_source.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(
  name = "weather_x_preferences"
)

class PreferencesManager @Inject constructor(
  @ApplicationContext private val context: Context
) {

  //region logic
  suspend fun putString(
    key: Preferences.Key<String>, value: String
  ) {
    putToDatastore { preferences ->
      preferences[key] = value
    }
  }

  fun getString(
    key: Preferences.Key<String>
  ): String = runBlocking {
    context.dataStore.data.map { it[key] ?: "" }.first()
  }

  suspend fun putBoolean(
    key: Preferences.Key<Boolean>, value: Boolean
  ) {
    putToDatastore { preferences ->
      preferences[key] = value
    }
  }

  fun getBoolean(
    key: Preferences.Key<Boolean>                ,
    defaultValue : Boolean = false
  ): Boolean = runBlocking {
    context.dataStore.data.map { it[key] ?: defaultValue }.first()
  }
  //endregion

  //region helper
  private suspend fun putToDatastore(
    putBlock: suspend (MutablePreferences) -> Unit
  ) {
    context.dataStore.edit(putBlock)
  }
  //endregion
}