package com.nedaluof.data.data_source.remote.api

import com.nedaluof.data.data_source.remote.model.GetForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created By NedaluOf - 1/17/2025.
 */
interface WeatherXApiService {

  @GET("forecast")
  suspend fun requestForecastData(
    @Query("lat") latitude: Double,
    @Query("lon") longitude: Double,
    @Query("units") units: String = "metric",
    @Query("appid") apiKey: String
  ): Response<GetForecastResponse>
}