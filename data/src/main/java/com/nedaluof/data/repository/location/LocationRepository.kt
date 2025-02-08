package com.nedaluof.data.repository.location

/**
 * Created By NedaluOf - 1/24/2025.
 */
interface LocationRepository {

  suspend fun saveLocation(
    coordinates: Pair<Double, Double>,
    onSaved: () -> Unit
  )

  fun loadLocation(): Pair<Double, Double>
}