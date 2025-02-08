package com.nedaluof.weatherx.ui.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nedaluof.domain.model.weather.WeatherModel
import com.nedaluof.weatherx.ui.common.AppErrorWidget
import com.nedaluof.weatherx.ui.common.AppLoadingIndicator
import com.nedaluof.weatherx.ui.navigation.WeatherXScreens
import com.nedaluof.weatherx.ui.screens.home.components.ChooseWeatherUnitDialog
import com.nedaluof.weatherx.ui.screens.home.components.CurrentDayWeatherDetails
import com.nedaluof.weatherx.ui.screens.home.components.HomeAppBar
import com.nedaluof.weatherx.ui.screens.home.components.PickLocationIndicator
import com.nedaluof.weatherx.ui.screens.home.components.WeekWeatherDetails
import com.nedaluof.weatherx.utils.contains
import com.nedaluof.weatherx.utils.removeData

/**
 * Created By NedaluOf - 1/15/2025.
 */
@Composable
fun HomeScreen(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController(),
  viewModel: HomeViewModel = hiltViewModel()
) {
  if (navController.contains("reload")) {
    navController.removeData<Boolean>("reload")
    viewModel.reloadWeatherData()
  }

  val uiState by viewModel.weatherUiState.collectAsStateWithLifecycle()
  when (uiState) {
    is WeatherUiState.Idle -> Unit
    is WeatherUiState.Loading -> AppLoadingIndicator(modifier = Modifier.fillMaxSize())
    is WeatherUiState.PickLocation -> {
      PickLocationIndicator(onPickClicked = { navController.navigate(WeatherXScreens.PICK_LOCATION.route) })
    }

    is WeatherUiState.Error -> {
      val errorMessage = (uiState as WeatherUiState.Error).errorMessage
      AppErrorWidget(message = errorMessage)
    }

    is WeatherUiState.WeatherData -> {
      (uiState as WeatherUiState.WeatherData).data?.let { data ->
        HomeScreenScaffold(
          modifier = modifier,
          weatherModel = data,
          navController = navController,
          currentWeatherUnitIsMetric = viewModel.currentWeatherScaleUnitIsMetric(),
          onWeatherScaleUnitChanged = viewModel::updateWeatherScaleUnit
        )
      }
    }
  }
}

@Composable
fun HomeScreenScaffold(
  modifier: Modifier = Modifier,
  weatherModel: WeatherModel,
  navController: NavHostController = rememberNavController(),
  currentWeatherUnitIsMetric: Boolean = true,
  onWeatherScaleUnitChanged: (isMetricSelected: Boolean) -> Unit = {}
) {
  var showChooseWeatherUnitDialog by remember { mutableStateOf(false) }
  Scaffold(
    modifier = modifier.fillMaxSize(),
    contentWindowInsets = WindowInsets(0.dp),
    topBar = {
    HomeAppBar(
      titleText = weatherModel.countryAndCityName,
      currentWeatherUnitIsMetric = currentWeatherUnitIsMetric,
      onChooseCityClicked = {
        navController.navigate(WeatherXScreens.PICK_LOCATION.route)
      },
      onWeatherUnitClicked = { showChooseWeatherUnitDialog = true }
    )
  }) { innerPaddings ->
    HomeScreenContent(
      modifier = Modifier.padding(innerPaddings), weatherModel = weatherModel
    )
  }

  if (showChooseWeatherUnitDialog) {
    ChooseWeatherUnitDialog(
      lastSelectionIsMetric = currentWeatherUnitIsMetric,
      onDismiss = { showChooseWeatherUnitDialog = false },
      onConfirm = onWeatherScaleUnitChanged
    )
  }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenContent(
  modifier: Modifier = Modifier,
  weatherModel: WeatherModel = WeatherModel.fake(),
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(top = 16.dp)
      .navigationBarsPadding()
      .imePadding()
      .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    CurrentDayWeatherDetails(
      weatherData = weatherModel.current,
      sunriseTime = weatherModel.sunrise,
      sunsetTime = weatherModel.sunset
    )
    WeekWeatherDetails(days = weatherModel.nextDaysForecast)
  }
}