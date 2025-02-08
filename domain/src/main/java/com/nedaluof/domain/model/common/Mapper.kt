package com.nedaluof.domain.model.common

/**
 * Created By NedaluOf - 1/24/2025.
 */
interface Mapper<FROM_MODEL, TO_MODEL> {

  fun toModel(input: FROM_MODEL): TO_MODEL

  fun toList(input: List<FROM_MODEL>): List<TO_MODEL> {
    return input.map { toModel(it) }
  }
}