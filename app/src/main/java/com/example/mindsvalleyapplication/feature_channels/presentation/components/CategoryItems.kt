package com.example.mindsvalleyapplication.feature_channels.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
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
import com.example.mindsvalleyapplication.ui.theme.dimens
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
                .fillMaxHeight()
                .padding(horizontal = MaterialTheme.dimens.categoryChipPadding, vertical = 10.dp)) {
          Box(
              modifier =
                  Modifier.height(MaterialTheme.dimens.categoryChipHeight)
                      .weight(0.5f)
                      .background(
                          shape = CircleShape, color = colorResource(id = R.color.chip_bg_color)),
          ) {
              CustomTextView(
                  modifier =
                  Modifier.fillMaxSize()
                      .padding(top = MaterialTheme.dimens.categoryChipTextPadding)
                      .align(Alignment.Center),
                  text = pair.first,
                  textSize = MaterialTheme.typography.labelSmall.fontSize,
                  textAlign = TextAlign.Center,
                  textColor = colorResource(id = R.color.white),
                  fontFamily = AppsFontUtils.fontBold,
                  fontWeight = FontWeight(700),
                  letterSpacing = TextUnit(value = 0.4f, TextUnitType.Sp))

          }
          Spacer(modifier = Modifier.width(MaterialTheme.dimens.categoryChipsBetweenPadding))
          Box(
              modifier =
                  Modifier.height(MaterialTheme.dimens.categoryChipHeight)
                      .weight(0.5f)
                      .background(
                          shape = CircleShape, color = colorResource(id = R.color.chip_bg_color)),
              contentAlignment = Alignment.Center) {
                pair.second?.let {
                  CustomTextView(
                      modifier =
                          Modifier.fillMaxSize()
                              .padding(top = MaterialTheme.dimens.categoryChipTextPadding)
                              .align(Alignment.Center),
                      text = it,
                      textSize = MaterialTheme.typography.labelSmall.fontSize,
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
