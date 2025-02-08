package com.nedaluof.weatherx.ui.screens.home.pick_location.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nedaluof.weatherx.R
import com.nedaluof.weatherx.ui.theme.Pink80
import com.nedaluof.weatherx.ui.theme.TransparentWhite

/**
 * Created By NedaluOf - 1/31/2025.
 */

@Preview
@Composable
fun PickLocationTopBar(
  modifier: Modifier = Modifier,
  onSearchQueryChanged: (String) -> Unit = {},
  onUseMyLocationClicked: () -> Unit = {},
  onBackClicked: () -> Unit = {},
) {
  Column(modifier = modifier.fillMaxWidth()) {
    LocationSearchBar(
      onSearchQueryChanged = onSearchQueryChanged,
      onBackClicked = onBackClicked
    )
    OutlinedButton(
      onClick = onUseMyLocationClicked,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 16.dp),
      shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
      Icon(
        imageVector = Icons.Default.MyLocation,
        contentDescription = null,
        tint = Pink80
      )
      Text(
        modifier = Modifier.padding(8.dp),
        text = stringResource(R.string.use_location_label),
        color = Pink80
      )
    }
  }
}


@Composable
fun LocationSearchBar(
  modifier: Modifier = Modifier,
  onSearchQueryChanged: (String) -> Unit = {},
  onBackClicked: () -> Unit = {},
) {
  var value by remember { mutableStateOf("") }
  OutlinedTextField(
    modifier = modifier
      .fillMaxWidth()
      .padding(top = 50.dp)
      .padding(horizontal = 16.dp),
    textStyle = LocalTextStyle.current.copy(
      fontSize = 14.sp
    ),
    shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    value = value,
    onValueChange = {
      value = it
      onSearchQueryChanged(it)
    },
    singleLine = true,
    leadingIcon = {
      IconButton(
        onClick = onBackClicked
      ) {
        Icon(
          modifier = Modifier.absolutePadding(),
          imageVector = Icons.Default.ArrowBackIosNew,
          contentDescription = null
        )
      }
    },
    trailingIcon = {
      Icon(
        imageVector = Icons.Default.Search,
        contentDescription = null
      )
    },
    label = {
      Text(
        text = stringResource(R.string.search_label),
        fontSize = 14.sp
      )
    },
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    colors = TextFieldDefaults.colors().copy(
      focusedTextColor = Color.White,
      cursorColor = Color.White,
      focusedContainerColor = Color.Transparent,
      unfocusedContainerColor = Color.Transparent,
      focusedPlaceholderColor = TransparentWhite,
      unfocusedPlaceholderColor = TransparentWhite,
      focusedIndicatorColor = Pink80,
      unfocusedIndicatorColor = TransparentWhite,
      focusedLabelColor = Pink80,
      unfocusedLabelColor = TransparentWhite,
      focusedTrailingIconColor = Pink80,
      unfocusedTrailingIconColor = TransparentWhite,
      focusedLeadingIconColor = Pink80,
      unfocusedLeadingIconColor = TransparentWhite
    )
  )
}