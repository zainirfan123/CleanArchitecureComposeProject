package com.example.mindsvalleyapplication.feature_channels.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.mindsvalleyapplication.R
import com.example.mindsvalleyapplication.feature_channels.presentation.components.ComposeUtils.CustomTextView
import com.example.mindsvalleyapplication.utils.AppsFontUtils

object CategoryItems {

  @Composable
  fun SetCategoryItems(categoryPairs: List<Pair<String, String?>>) {
    categoryPairs.forEach { CategoryChip(pair = it) }
  }

  @Composable
  fun CategoryChip(pair: Pair<String, String?>) {
    Row(
        modifier =
            Modifier.fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)) {
          Box(
              modifier =
                  Modifier.height(60.dp)
                      .weight(0.5f)
                      .background(
                          shape = CircleShape, color = colorResource(id = R.color.chip_bg_color)),
              contentAlignment = Alignment.Center) {
                CustomTextView(
                    modifier =
                        Modifier.fillMaxHeight()
                            .padding(top = 20.dp, start = 2.dp, end = 2.dp)
                            .align(Alignment.Center),
                    text = pair.first,
                    textSize = 18,
                    textAlign = TextAlign.Center,
                    textColor = colorResource(id = R.color.white),
                    fontFamily = AppsFontUtils.fontBold,
                    fontWeight = FontWeight(700),
                    letterSpacing = TextUnit(value = 0.4f, TextUnitType.Sp))
              }
          Spacer(modifier = Modifier.width(20.dp))
          Box(
              modifier =
                  Modifier.height(60.dp)
                      .weight(0.5f)
                      .background(
                          shape = CircleShape, color = colorResource(id = R.color.chip_bg_color)),
              contentAlignment = Alignment.Center) {
                pair.second?.let {
                  CustomTextView(
                      modifier =
                          Modifier.fillMaxHeight()
                              .padding(top = 20.dp, start = 2.dp, end = 2.dp)
                              .align(Alignment.Center),
                      text = it,
                      textSize = 18,
                      textAlign = TextAlign.Center,
                      textColor = colorResource(id = R.color.white),
                      fontFamily = AppsFontUtils.fontBold,
                      fontWeight = FontWeight(700),
                      letterSpacing = TextUnit(value = 0.4f, TextUnitType.Sp))
                }
              }
        }
  }
}
