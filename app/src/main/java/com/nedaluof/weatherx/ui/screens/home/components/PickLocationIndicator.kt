package com.nedaluof.weatherx.ui.screens.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nedaluof.weatherx.R
import com.nedaluof.weatherx.ui.theme.Pink40

/**
 * Created By NedaluOf - 2/2/2025.
 */
@Preview
@Composable
fun PickLocationIndicator(
  modifier: Modifier = Modifier,
  onPickClicked: () -> Unit = {}
) {
  Box(modifier = modifier.fillMaxSize()) {
    Surface(
      modifier = Modifier
        .align(Alignment.Center)
        .fillMaxWidth()
        .padding(24.dp),
      color = Pink40,
      shape = RoundedCornerShape(CornerSize(16.dp))
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Icon(
          modifier = Modifier
            .size(60.dp)
            .padding(top = 24.dp),
          imageVector = Icons.Default.MyLocation,
          contentDescription = null
        )
        Text(
          modifier = Modifier.padding(top = 16.dp),
          text = stringResource(R.string.pick_location_description_label),
          textAlign = TextAlign.Center,
          fontSize = 18.sp,
          lineHeight = 24.sp,
          letterSpacing = 2.sp,
          color = Color.White
        )

        Button(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 16.dp),
          onClick = onPickClicked,
          colors = ButtonDefaults.buttonColors().copy(
            containerColor = Color.White
          )
        ) {
          Text(
            text = stringResource(R.string.pick_location_label),
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.Black,
            letterSpacing = 2.sp
          )
        }
      }
    }
  }
}