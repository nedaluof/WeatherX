package com.nedaluof.weatherx.ui.screens.home.pick_location.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nedaluof.domain.model.city.CityModel
import com.nedaluof.weatherx.ui.common.AppDivider
import com.nedaluof.weatherx.ui.theme.Pink40
import com.nedaluof.weatherx.ui.theme.TransparentWhite

/**
 * Created By NedaluOf - 2/2/2025.
 */
@Preview
@Composable
fun CountryDetailsItem(
  modifier: Modifier = Modifier,
  city: CityModel = CityModel.fake(),
  onClicked: () -> Unit = {}
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 4.dp),
    onClick = onClicked,
    shape = RoundedCornerShape(0.dp),
    colors = CardDefaults.cardColors().copy(containerColor = Color.Transparent),
    content = {
      Text(
        modifier = Modifier
          .padding(horizontal = 16.dp)
          .padding(bottom = 8.dp),
        text = buildAnnotatedString {
          withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
            withStyle(
              style = SpanStyle(
                color = TransparentWhite,
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                letterSpacing = 1.5.sp
              )
            ) {
              append("${city.countryName}/ ")
              withStyle(
                style = SpanStyle(
                  color = Pink40,
                  fontWeight = FontWeight.SemiBold,
                  fontSize = 20.sp
                )
              ) {
                append(city.cityName)
              }
            }
          }
        }
      )
      AppDivider(height = 0.9.dp)
    }
  )
}