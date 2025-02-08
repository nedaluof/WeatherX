package com.nedaluof.domain.model.weather

import com.nedaluof.data.BuildConfig
import com.nedaluof.data.data_source.remote.model.GetForecastResponse
import com.nedaluof.domain.model.common.Mapper
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.math.roundToInt

/**
 * Created By NedaluOf - 1/24/2025.
 */
class WeatherModelMapper @Inject constructor() : Mapper<GetForecastResponse, WeatherModel> {

  //region logic
  override fun toModel(input: GetForecastResponse): WeatherModel {
    val daysWeatherDataModelList = mutableListOf<WeatherDataModel>()
    input.list?.groupBy {
      it?.dataTimeEpoch?.let { dataTimeEpoch ->
        Instant.ofEpochSecond(dataTimeEpoch)
          .atZone(ZoneId.systemDefault())
          .toLocalDate()
      }
    }?.forEach { (date, forecasts) ->
      val firstForecast = forecasts.first()
      daysWeatherDataModelList.add(
        WeatherDataModel(
          temperature = "${firstForecast?.main?.temperature?.roundToInt()}",
          iconUrl = firstForecast?.weather?.get(0)?.icon.weatherIcon(),
          minTemperature = "${firstForecast?.main?.minTemperature?.roundToInt()}",
          maxTemperature = "${firstForecast?.main?.maxTemperature?.roundToInt()}",
          weatherStatus = "${firstForecast?.weather?.get(0)?.main}",
          feelsLike = "${firstForecast?.main?.feelsLike?.roundToInt()}",
          pressure = "${firstForecast?.main?.pressure}",
          humidity = "${firstForecast?.main?.humidity}",
          dayName = date?.dayOfWeek?.toString() ?: ""
        )
      )
    }
    return WeatherModel(
      countryAndCityName = "${input.city?.name}, ${input.city?.country}",
      sunrise = input.city?.sunrise.formatTime(),
      sunset = input.city?.sunset.formatTime(),
      current = daysWeatherDataModelList[0],
      nextDaysForecast = daysWeatherDataModelList.drop(0)
    )
  }
  //endregion

  //region util
  private fun String?.weatherIcon() = "${BuildConfig.WEATHER_IMAGE_URL}$this@2x.png"
  
  private fun Long?.formatTime(): String {
    return this?.let {
      val localDateTime = Instant.ofEpochSecond(it)
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
      val formatter = DateTimeFormatter.ofPattern("hh:mm a")
      localDateTime.format(formatter)
    } ?: run { "" }
  }
  //endregion
}