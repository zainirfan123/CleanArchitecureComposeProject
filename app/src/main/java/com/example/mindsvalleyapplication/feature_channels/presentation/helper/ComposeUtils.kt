package com.example.mindsvalleyapplication.feature_channels.presentation.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.mindsvalleyapplication.R
import com.example.mindsvalleyapplication.feature_channels.domain.model.CustomizeResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.HorizontalItemModel

object ComposeUtils {

  @Composable
  fun CustomTextView(
      text: String,
      modifier: Modifier = Modifier,
      textSize: Int = 16,
      textColor: Color = Color.Black,
      textAlign: TextAlign? = TextAlign.Start,
      fontFamily: FontFamily? = null,
      fontWeight: FontWeight? = null,
      letterSpacing: TextUnit
  ) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = textSize.sp,
        color = textColor,
        textAlign = textAlign,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        letterSpacing = letterSpacing,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis)
  }

  @Composable
  fun Divider() {
    Spacer(modifier = Modifier.height(30.dp))
    Image(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(horizontal = 20.dp),
        painter = painterResource(id = R.drawable.divider),
        contentDescription = "divider",
        contentScale = ContentScale.Crop)
    Spacer(modifier = Modifier.height(10.dp))
  }

  @Composable
  fun SetHorizontalItems(list: List<HorizontalItemModel>) {
    LazyRow(modifier = Modifier.fillMaxSize().wrapContentHeight()) {
      itemsIndexed(list) { index: Int, item: HorizontalItemModel ->
        if (index < 6) {
          Spacer(modifier = Modifier.width(10.dp))
          RowItems(item = item)
          Spacer(modifier = Modifier.width(10.dp))
        }
      }
    }
  }

  @Composable
  fun RowItems(item: HorizontalItemModel) {
    Column(
        modifier = Modifier.width(152.dp).wrapContentHeight(),
        horizontalAlignment = Alignment.Start) {
          val painter = rememberImagePainter(item.image)

          Image(
              modifier = Modifier.fillMaxWidth().height(228.dp).clip(RoundedCornerShape(10.dp)),
              painter = painter,
              contentDescription = item.title,
              contentScale = ContentScale.Crop)
          Spacer(modifier = Modifier.height(10.dp))

          CustomTextView(
              modifier = Modifier.padding(start = 10.dp),
              text = item.title,
              textSize = 17,
              textColor = colorResource(id = R.color.white),
              fontFamily = AppsFontUtils.fontBold,
              letterSpacing = TextUnit(value = 0.4f, TextUnitType.Sp))

          if (!item.subTitle.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(10.dp))

            CustomTextView(
                modifier = Modifier.width(142.dp).padding(start = 10.dp),
                text = item.subTitle.uppercase(),
                textSize = 13,
                textColor = colorResource(id = R.color.grey_secondary),
                fontFamily = AppsFontUtils.fontBold,
                letterSpacing = TextUnit(value = 0.4f, TextUnitType.Sp))
          }
        }
  }

  @Composable
  fun SetChannels(context: Context, list: List<CustomizeResponseModel>) {
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
      list.forEach { item ->
        Row(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
          val drawableResourceId = getDrawableResourceIdFromSlug(item.slug, context)
          val painter =
              if (drawableResourceId != null && drawableResourceId != 0) {
                painterResource(id = drawableResourceId)
              } else {
                Log.e("Drawable", "Drawable resource not found for slug: ${item.slug}")
                painterResource(id = R.drawable.mind_valley_mentoring)
              }
          titleToDrawableMap[item.title]
              ?.let { painterResource(id = it) }
              ?.let {
                Image(
                    modifier = Modifier.width(50.dp).height(50.dp),
                    painter = it,
                    contentDescription = item.title,
                    contentScale = ContentScale.Crop)
              }

          Spacer(modifier = Modifier.height(20.dp))

          Column(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
            CustomTextView(
                modifier = Modifier.padding(start = 10.dp, top = 5.dp),
                text = item.title,
                textSize = 20,
                textColor = colorResource(id = R.color.white),
                fontFamily = AppsFontUtils.fontBold,
                fontWeight = FontWeight(700),
                letterSpacing = TextUnit(value = 0.4f, TextUnitType.Sp))
            CustomTextView(
                modifier = Modifier.padding(start = 10.dp, top = 5.dp),
                text = item.numOfEpisodes,
                textSize = 16,
                textColor = colorResource(id = R.color.grey_secondary),
                fontFamily = AppsFontUtils.fontBold,
                fontWeight = FontWeight(600),
                letterSpacing = TextUnit(value = 0.4f, TextUnitType.Sp))
          }
        }

        Spacer(modifier = Modifier.height(20.dp))

        SetHorizontalItems(list = item.items)
        Divider()
      }
    }
  }

  private fun getDrawableResourceIdFromSlug(slug: String?, context: Context): Int {
    // If the slug is null, return the default resource
    if (slug == null) {
      Log.e("Drawable", "Slug is null, returning default resource")
      return R.drawable.mind_valley_mentoring
    }
    // Get the resource ID of the drawable dynamically
    val resourceId = context.resources.getIdentifier(slug, "drawable", context.packageName)
    if (resourceId == 0) {
      Log.e("Drawable", "Drawable resource not found for slug: $slug")
    }
    return resourceId
  }

  // Define your mapping of titles to drawable resource names
  val titleToDrawableMap =
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

  @Composable
  fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
      CircularProgressIndicator()
    }
  }

  @Composable
  fun ErrorScreen(errorMessage: String) {
    Box(modifier = Modifier.fillMaxSize().padding(20.dp), contentAlignment = Alignment.Center) {
      Text(
          text = "Error: $errorMessage",
          color = Color.Red,
          textAlign = TextAlign.Center,
          fontFamily = AppsFontUtils.fontBold,
          modifier = Modifier.padding(horizontal = 16.dp))
    }
  }

  @Composable
  fun ShowCategories(categoryPairs: List<Pair<String, String?>>) {
    categoryPairs.forEach { CategoryChip(pair = it) }
  }

  @Composable
  fun CategoryChip(pair: Pair<String, String?>) {
    Row(modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(10.dp)) {
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


    fun isInternetConnected(context: Context): Boolean {
        // Access the ConnectivityManager
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Check for internet connectivity
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(
                NetworkCapabilities.TRANSPORT_CELLULAR
            ))
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            networkInfo != null && networkInfo.isConnected
        }
    }
}
