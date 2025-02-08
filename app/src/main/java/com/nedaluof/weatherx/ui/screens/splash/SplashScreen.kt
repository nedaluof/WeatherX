package com.nedaluof.weatherx.ui.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.nedaluof.weatherx.R
import com.nedaluof.weatherx.ui.navigation.WeatherXScreens
import kotlinx.coroutines.delay

/**
 * Created By NedaluOf - 1/12/2025.
 */
@Preview
@Composable
fun SplashScreen(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController()
) {
  val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_animation_new))
  val progress by animateLottieCompositionAsState(
    composition,
    iterations = LottieConstants.IterateForever
  )
  val scale = remember { Animatable(0f) }
  LaunchedEffect(true) {
    scale.animateTo(
      targetValue = 0.9f,
      animationSpec = tween(
        durationMillis = 800,
        easing = {
          OvershootInterpolator(8f).getInterpolation(it)
        }
      )
    )
    delay(2000)
    navController.navigate(WeatherXScreens.HOME.route)
  }
  Box(modifier = modifier.fillMaxSize()) {
    LottieAnimation(
      modifier = Modifier
        .align(Alignment.Center)
        .scale(scale.value),
      composition = composition,
      progress = { progress }
    )
  }
}