package com.nedaluof.weatherx.ui.screens.home.pick_location.fetch_location

import android.Manifest
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.nedaluof.weatherx.R
import com.nedaluof.weatherx.ui.common.AppLoadingIndicator
import com.nedaluof.weatherx.utils.loadCurrentLocation
import com.nedaluof.weatherx.utils.setData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * Created By NedaluOf - 1/24/2025.
 */

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FetchLocationScreen(
  modifier: Modifier = Modifier,
  navController: NavController
) {
  val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
  if (locationPermissionState.status.isGranted) {
    FetchLocation(
      modifier = modifier,
      onLocationFetched = { location ->
        navController.setData("latitude", location.latitude)
        navController.setData("longitude", location.longitude)
        navController.popBackStack()
      }
    )
  } else {
    GrantLocationPermission(modifier = modifier)
  }
}

@Composable
fun FetchLocation(
  modifier: Modifier = Modifier,
  onLocationFetched: (Location) -> Unit
) {
  val context = LocalContext.current
  SideEffect {
    loadCurrentLocation(
      context,
      onGetCurrentLocationSuccess = onLocationFetched
    )
  }
  AppLoadingIndicator(modifier = modifier.fillMaxSize())
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GrantLocationPermission(
  modifier: Modifier = Modifier,
  onPermissionGranted: () -> Unit = {}
) {
  val context = LocalContext.current
  val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
  val permissionStatus = locationPermissionState.status
  val isFirstRequest = remember { mutableStateOf(true) }
  var isGoToSettings by remember { mutableStateOf(false) }
  var isGranted by remember { mutableStateOf(false) }
  var buttonStringResourceId by remember { mutableIntStateOf(R.string.grant_permission_label) }
  LaunchedEffect(permissionStatus) {
    isGranted = permissionStatus.isGranted
    if (isGranted) {
      delay(1500)
      withContext(Dispatchers.Main) {
        onPermissionGranted()
      }
    }
  }
  if (isFirstRequest.value || permissionStatus.shouldShowRationale) {
    isGoToSettings = false
    buttonStringResourceId = R.string.grant_permission_label
  } else {
    isGoToSettings = true
    buttonStringResourceId = R.string.go_to_settings_label
  }

  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(all = 16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_animation_new))
      val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
      )
      LottieAnimation(
        modifier = Modifier,
        composition = composition,
        progress = { progress }
      )
      if (!isGranted) {
        Text(
          modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp),
          text = stringResource(R.string.location_permission_description),
          color = Color.White,
          fontSize = 18.sp,
          textAlign = TextAlign.Center,
          lineHeight = 30.sp
        )
        Button(
          modifier = Modifier.padding(top = 16.dp),
          onClick = {
            if (!isGoToSettings) {
              isFirstRequest.value = false
              locationPermissionState.launchPermissionRequest()
            } else {
              val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName, null)
              }
              context.startActivity(intent)
            }
          }
        ) {
          Text(text = stringResource(buttonStringResourceId))
        }
      } else {
        CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
      }
    }
  }
}