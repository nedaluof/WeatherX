package com.nedaluof.data.data_source.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

/**
 * Created By NedaluOf - 1/30/2025.
 */
@JsonClass(generateAdapter = true)
@Entity(tableName = "cities")
data class CityEntity(
  @PrimaryKey
  val id: Int,
  val country: String,
  val city: String,
  val latitude: Double,
  val longitude: Double,
  val altitude: Double
)

fun jsonStringToCities(jsonString: String): List<CityEntity> {
  val moshi = Moshi.Builder().build()
  val adapter: JsonAdapter<List<CityEntity>> =
    moshi.adapter(Types.newParameterizedType(List::class.java, CityEntity::class.java))
  return adapter.fromJson(jsonString) ?: emptyList()
}