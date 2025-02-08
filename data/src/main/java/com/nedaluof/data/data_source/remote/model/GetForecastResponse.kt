package com.nedaluof.data.data_source.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created By NedaluOf - 1/17/2025.
 */
@JsonClass(generateAdapter = true)
data class GetForecastResponse(
  val city: City? = null,
  val cnt: Int? = null,
  val cod: String? = null,
  val list: List<ForecastDto?>? = null,
  val message: Int? = null
)

@JsonClass(generateAdapter = true)
data class City(
  @Json(name = "coord")
  val coordinates: ForecastCoordinates? = null,
  val country: String? = null,
  val id: Int? = null,
  val name: String? = null,
  val population: Int? = null,
  val sunrise: Long? = null,
  val sunset: Long? = null,
  val timezone: Int? = null
)

@JsonClass(generateAdapter = true)
data class Clouds(
  val all: Int? = null
)

@JsonClass(generateAdapter = true)
data class ForecastCoordinates(
  @Json(name = "lat")
  val lat: Double? = null,
  @Json(name = "lon")
  val lon: Double? = null
)

@JsonClass(generateAdapter = true)
data class ForecastDto(
  val clouds: Clouds? = null,
  @Json(name = "dt")
  val dataTimeEpoch: Long? = null,
  @Json(name = "dt_txt")
  val dateTimeText: String? = null,
  val main: Main? = null,
  val visibility: Int? = null,
  val weather: List<Weather?>? = null,
  val wind: Wind? = null
)

@JsonClass(generateAdapter = true)
data class Main(
  @Json(name = "feels_like")
  val feelsLike: Double? = null,
  @Json(name = "grnd_level")
  val groundLevel: Int? = null,
  val humidity: Int? = null,
  val pressure: Int? = null,
  @Json(name = "sea_level")
  val seaLevel: Int? = null,
  @Json(name = "temp")
  val temperature: Double? = null,
  @Json(name = "temp_kf")
  val temperatureKf: Double? = null,
  @Json(name = "temp_max")
  val maxTemperature: Double? = null,
  @Json(name = "temp_min")
  val minTemperature: Double? = null
)

@JsonClass(generateAdapter = true)
data class Weather(
  val description: String? = null,
  val icon: String? = null,
  val id: Int? = null,
  val main: String? = null
)

@JsonClass(generateAdapter = true)
data class Wind(
  @Json(name = "deg")
  val degree: Int? = null,
  val gust: Double? = null,
  val speed: Double? = null
)