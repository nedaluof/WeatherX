package com.nedaluof.domain.model.city

/**
 * Created By NedaluOf - 2/4/2025.
 */
data class CityModel(
  val countryName: String,
  val cityName: String,
  val coordinates: Pair<Double, Double>
) {
  companion object {
    fun fake() = CityModel(
      "Jordan", "Amman", Pair(0.0, 0.0)
    )
  }
}