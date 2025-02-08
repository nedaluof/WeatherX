package com.nedaluof.weatherx.utils

import androidx.navigation.NavController

/**
 * Created By NedaluOf - 2/7/2025.
 */
fun <T> NavController.setData(key: String, value: T) {
  this.previousBackStackEntry?.savedStateHandle?.set<T>(key, value)
}

fun <T> NavController.getData(key: String): T? {
  return this.currentBackStackEntry?.savedStateHandle?.get<T>(key)
}

fun <T> NavController.removeData(key: String) {
  this.currentBackStackEntry?.savedStateHandle?.remove<T>(key)
}

fun NavController.contains(key: String): Boolean {
  return this.currentBackStackEntry?.savedStateHandle?.contains(key) == true
}