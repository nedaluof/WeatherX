package com.nedaluof.weatherx.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nedaluof.weatherx.ui.screens.home.HomeScreen
import com.nedaluof.weatherx.ui.screens.home.pick_location.PickLocationScreen
import com.nedaluof.weatherx.ui.screens.home.pick_location.fetch_location.FetchLocationScreen
import com.nedaluof.weatherx.ui.screens.splash.SplashScreen
import com.nedaluof.weatherx.utils.getActivity

/**
 * Created By NedaluOf - 1/12/2025.
 */
@Composable
fun WeatherXNavigationGraph(modifier: Modifier = Modifier) {
  val context = LocalContext.current
  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = WeatherXScreens.SPLASH.route
  ) {
    composable(route = WeatherXScreens.SPLASH.route) {
      SplashScreen(modifier = modifier, navController = navController)
    }
    composable(route = WeatherXScreens.HOME.route) {
      HomeScreen(modifier = modifier, navController = navController)
    }
    composable(route = WeatherXScreens.PICK_LOCATION.route) {
      PickLocationScreen(modifier = modifier, navController = navController)
    }
    composable(route = WeatherXScreens.FETCH_LOCATION.route) {
      FetchLocationScreen(modifier = modifier, navController = navController)
    }
  }

  BackHandler(
    enabled = navController.currentBackStackEntry?.destination?.route == WeatherXScreens.SPLASH.route
  ) {
    if (navController.currentBackStackEntry?.destination?.route != WeatherXScreens.HOME.route) {
      navController.popBackStack()
    } else {
      context.getActivity()?.finish()
    }
  }
}