package com.example.mindsvalleyapplication.feature_channels.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.mindsvalleyapplication.R
import com.example.mindsvalleyapplication.feature_channels.domain.model.CustomizeChannelResponseModel
import com.example.mindsvalleyapplication.feature_channels.presentation.components.ComposeUtils.CustomTextView
import com.example.mindsvalleyapplication.feature_channels.presentation.components.ComposeUtils.Divider
import com.example.mindsvalleyapplication.feature_channels.presentation.components.RowItems.SetRowItems
import com.example.mindsvalleyapplication.utils.AppsFontUtils

object ChannelItems {

  @Composable
  fun SetCourseOrSeriesItems(list: List<CustomizeChannelResponseModel>) {
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
      list.forEach { item ->
        Row(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
          titleToDrawableMap[item.title]
              ?.let { painterResource(id = it) }
              ?.let {
                Image(
                    modifier =
                        Modifier.width(50.dp).height(50.dp),
                    painter = it,
                    contentDescription = item.title,
                    contentScale = ContentScale.Crop)
              }

          Spacer(modifier = Modifier.height(20.dp))

          Column(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
            CustomTextView(
                modifier =
                    Modifier.padding(start = 10.dp, top = 5.dp),
                text = item.title,
                textSize = 20,
                textColor = colorResource(id = R.color.white),
                fontFamily = AppsFontUtils.fontBold,
                fontWeight = FontWeight(700),
                letterSpacing = TextUnit(value = 0.4f, TextUnitType.Sp))
            CustomTextView(
                modifier =
                    Modifier.padding(start = 10.dp, top = 5.dp),
                text = item.numOfEpisodes,
                textSize = 16,
                textColor = colorResource(id = R.color.grey_secondary),
                fontFamily = AppsFontUtils.fontBold,
                fontWeight = FontWeight(600),
                letterSpacing = TextUnit(value = 0.4f, TextUnitType.Sp))
          }
        }

        Spacer(modifier = Modifier.height(20.dp))

        SetRowItems(list = item.items)
        Divider()
      }
    }
  }

  // Define your mapping of titles to drawable resource names
  private val titleToDrawableMap =
      mapOf(
          "Mentoring At Work" to R.drawable.impact_at_work,
          "Lifebook Membership" to R.drawable.life_book_membership,
          "Lifebook Accountability System" to R.drawable.life_book_membership,
          "Coaching Mastery by Evercoach" to R.drawable.coaching_mastery,
          "Master's Circle" to R.drawable.coaching_mastery,
          "Mindvalley Films" to R.drawable.mind_valley_films,
          "Mindvalley Mentoring" to R.drawable.mind_valley_mentoring,
          "Little Humans" to R.drawable.mind_valley_mentoring,
          "Unlimited Abundance" to R.drawable.mind_valley_mentoring,
      )
}
