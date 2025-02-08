package com.nedaluof.domain.model.weather

import com.nedaluof.data.BuildConfig

/**
 * Created By NedaluOf - 1/24/2025.
 */
data class WeatherModel(
  val countryAndCityName: String = "",
  val sunrise: String = "",
  val sunset: String = "",
  val current: WeatherDataModel = WeatherDataModel(),
  val nextDaysForecast: List<WeatherDataModel> = emptyList()
) {
  companion object {
    fun fake() = WeatherModel(
      countryAndCityName = "Amman, Jo",
      current = WeatherDataModel.fake(),
      sunrise = "6:00 AM",
      sunset = "7:00 PM",
      nextDaysForecast = listOf(
        WeatherDataModel.fake(),
        WeatherDataModel.fake(),
        WeatherDataModel.fake()
      )
    )
  }
}

data class WeatherDataModel(
  val temperature: String = "",
  val iconUrl: String = "",
  val minTemperature: String = "",
  val maxTemperature: String = "",
  val weatherStatus: String = "",
  val dayName: String = "",
  val feelsLike: String = "",
  val humidity: String = "",
  val pressure: String = ""
) {
  companion object {
    fun fake() = WeatherDataModel(
      temperature = "20",
      iconUrl = "${BuildConfig.WEATHER_IMAGE_URL}03d@2x.png",
      minTemperature = "3",
      maxTemperature = "20",
      weatherStatus = "Clouds",
      dayName = "Sunday",
      feelsLike = "15",
      humidity = "50",
      pressure = "100"
    )
  }
}