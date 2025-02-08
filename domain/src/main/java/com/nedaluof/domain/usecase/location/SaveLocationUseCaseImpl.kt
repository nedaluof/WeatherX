package com.nedaluof.domain.usecase.location

import com.nedaluof.data.repository.location.LocationRepository
import com.nedaluof.domain.utils.post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By NedaluOf - 1/24/2025.
 */
class SaveLocationUseCaseImpl @Inject constructor(
  private val locationRepository: LocationRepository
) : SaveLocationUseCase {

  //region logic
  override fun saveLocation(
    scope: CoroutineScope,
    coordinates: Pair<Double, Double>,
    onSaved: () -> Unit
  ) {
    scope.launch(Dispatchers.IO) {
      locationRepository.saveLocation(coordinates) {
        scope.post(onSaved)
      }
    }
  }
  //endregion
}