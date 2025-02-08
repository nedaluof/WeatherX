package com.nedaluof.data.di

import com.nedaluof.data.BuildConfig.BASE_URL
import com.nedaluof.data.data_source.remote.api.WeatherXApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created By NedaluOf - 1/17/2025.
 */
@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

  //region constants
  private const val TIME_OUT_DURATION_IN_SECONDS = 180L
  //endregion

  //region logic
  @Singleton
  @Provides
  fun provideMoshi(): Moshi = Moshi.Builder().build()

  @Singleton
  @Provides
  fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(
      HttpLoggingInterceptor().also { interceptor ->
        interceptor.level = HttpLoggingInterceptor.Level.BODY
      }
    ).writeTimeout(TIME_OUT_DURATION_IN_SECONDS, TimeUnit.SECONDS)
    .callTimeout(TIME_OUT_DURATION_IN_SECONDS, TimeUnit.SECONDS)
    .readTimeout(TIME_OUT_DURATION_IN_SECONDS, TimeUnit.SECONDS)
    .build()

  @Singleton
  @Provides
  fun provideWeatherXApiService(
    okHttpClient: OkHttpClient, moshi: Moshi
  ): WeatherXApiService =
    Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl(BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
      .create(WeatherXApiService::class.java)
  //endregion
}