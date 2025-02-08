package com.nedaluof.weatherx.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

/**
 * Created By NedaluOf - 1/24/2025.
 */
@SuppressLint("MissingPermission")
fun loadCurrentLocation(
  context: Context,
  onGetCurrentLocationSuccess: (Location) -> Unit,
  onGetCurrentLocationFailed: (Exception) -> Unit = {}
) {
  LocationServices.getFusedLocationProviderClient(context).getCurrentLocation(
    Priority.PRIORITY_HIGH_ACCURACY,
    CancellationTokenSource().token,
  ).addOnSuccessListener { location ->
    location?.let(onGetCurrentLocationSuccess)
  }.addOnFailureListener(onGetCurrentLocationFailed)
}