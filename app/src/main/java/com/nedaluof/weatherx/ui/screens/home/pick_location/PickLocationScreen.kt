package com.nedaluof.weatherx.ui.screens.home.pick_location

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nedaluof.domain.model.city.CityModel
import com.nedaluof.weatherx.R
import com.nedaluof.weatherx.ui.common.AppErrorWidget
import com.nedaluof.weatherx.ui.common.AppLoadingIndicator
import com.nedaluof.weatherx.ui.navigation.WeatherXScreens
import com.nedaluof.weatherx.ui.screens.home.pick_location.components.ConfirmSelectedCityDialog
import com.nedaluof.weatherx.ui.screens.home.pick_location.components.CountryDetailsItem
import com.nedaluof.weatherx.ui.screens.home.pick_location.components.PickLocationTopBar
import com.nedaluof.weatherx.utils.contains
import com.nedaluof.weatherx.utils.getData
import com.nedaluof.weatherx.utils.removeData
import com.nedaluof.weatherx.utils.setData

/**
 * Created By NedaluOf - 1/30/2025.
 */
@Composable
fun PickLocationScreen(
  modifier: Modifier = Modifier,
  navController: NavController = rememberNavController(),
  viewModel: PickLocationViewModel = hiltViewModel()
) {
  SideEffect {
    if (navController.contains("latitude")) {
      val latitude = navController.getData<Double>("latitude") ?: 0.0
      val longitude = navController.getData<Double>("longitude") ?: 0.0
      viewModel.savePickedLocation(Pair(latitude, longitude)) {
        navController.removeData<Double>("latitude")
        navController.removeData<Double>("longitude")
        navController.setData("reload", true)
        navController.popBackStack()
      }
    }
  }

  val uiState by viewModel.citiesUiState.collectAsStateWithLifecycle()
  //val cities = remember { mutableStateListOf<CityModel>() }
  var searchQuery by remember { mutableStateOf("") }
  LaunchedEffect(searchQuery) {
    viewModel.loadCities(searchQuery)/*.collectLatest {
      cities.clear()
      cities.addAll(it)
    }*/
  }
  Scaffold(modifier = modifier.fillMaxSize(), topBar = {
    PickLocationTopBar(
      onUseMyLocationClicked = {
        navController.navigate(WeatherXScreens.FETCH_LOCATION.route)
      },
      onBackClicked = navController::popBackStack,
      onSearchQueryChanged = { query -> searchQuery = query })
  }) { innerPadding ->
    when (uiState) {
      is CitiesUiState.Idle -> Unit
      is CitiesUiState.Loading -> {
        AppLoadingIndicator(
          modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        )
      }

      is CitiesUiState.CityNotFound -> {
        AppErrorWidget(message = stringResource(R.string.city_not_found_label))
      }

      is CitiesUiState.Cities -> {
        val cities = (uiState as CitiesUiState.Cities).data
        CitiesList(
          modifier = Modifier.padding(innerPadding),
          cities = cities.toList(),
          onSelectCity = { cityModel ->
            viewModel.savePickedLocation(cityModel.coordinates) {
              navController.setData("reload", true)
              navController.popBackStack()
            }
          }
        )
      }
    }
  }
}

@Composable
fun CitiesList(
  modifier: Modifier = Modifier,
  cities: List<CityModel> = emptyList(),
  onSelectCity: (CityModel) -> Unit
) {
  var showConfirmationDialog by remember { mutableStateOf(false) }
  var selectedCity by remember { mutableStateOf<CityModel?>(null) }
  LazyColumn(modifier = modifier.fillMaxWidth()) {
    items(cities) { city ->
      CountryDetailsItem(city = city, onClicked = {
        selectedCity = city
        showConfirmationDialog = true
      })
    }
  }
  if (showConfirmationDialog) {
    ConfirmSelectedCityDialog(
      locationName = "${selectedCity?.countryName} / ${selectedCity?.cityName}",
      onDismiss = { showConfirmationDialog = false },
      onConfirm = { selectedCity?.let(onSelectCity) }
    )
  }
}