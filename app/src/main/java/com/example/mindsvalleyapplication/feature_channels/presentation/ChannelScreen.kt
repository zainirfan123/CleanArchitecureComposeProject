package com.example.mindsvalleyapplication.feature_channels.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mindsvalleyapplication.R
import com.example.mindsvalleyapplication.feature_channels.presentation.components.CategoryItems.SetCategoryItems
import com.example.mindsvalleyapplication.feature_channels.presentation.components.ChannelItems.SetCourseOrSeriesItems
import com.example.mindsvalleyapplication.feature_channels.presentation.components.ComposeUtils.CustomTextView
import com.example.mindsvalleyapplication.feature_channels.presentation.components.ComposeUtils.Divider
import com.example.mindsvalleyapplication.feature_channels.presentation.components.ComposeUtils.ErrorScreen
import com.example.mindsvalleyapplication.feature_channels.presentation.components.RowItems.SetRowItems
import com.example.mindsvalleyapplication.ui.theme.dimens
import com.example.mindsvalleyapplication.utils.AppsFontUtils
import com.example.mindsvalleyapplication.utils.Extensions.isInternetConnected
import com.example.mindsvalleyapplication.utils.TestTags
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ChannelScreen(
    navController: NavController,
    viewModel: ChannelScreenViewModel = hiltViewModel()
) {
  InitializeChannelUI(viewModel = viewModel)
}

@Composable
private fun InitializeChannelUI(viewModel: ChannelScreenViewModel) {
  val channel = viewModel.state.value.response
  val episodes = viewModel.episodeState.value.response?.data
  val categories = viewModel.categoriesState.value.response?.data
  val context = LocalContext.current
  Box(modifier = Modifier.fillMaxSize().background(colorResource(id = R.color.channel_bg))) {
    // check error states
    CheckErrorStates(viewModel)
    val swipeRefreshState =
        rememberSwipeRefreshState(
            isRefreshing =
                viewModel.state.value.isLoading ||
                    viewModel.episodeState.value.isLoading ||
                    viewModel.categoriesState.value.isLoading)
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
          viewModel.handleScreenEvent(context.isInternetConnected(), ChannelScreenEvents.Refresh)
        }) {
          LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(5.dp)) {
            item {
              Spacer(modifier = Modifier.height(MaterialTheme.dimens.channelTitleTopSpace))
              CustomTextView(
                  modifier = Modifier.padding(start = MaterialTheme.dimens.channelIconStartSpace).testTag(TestTags.CHANNEL_APP_TITLE),
                  text = stringResource(id = R.string.channel_title),
                  textSize = MaterialTheme.typography.headlineLarge.fontSize,
                  textColor = colorResource(id = R.color.grey),
                  fontFamily = AppsFontUtils.fontBold,
                  fontWeight = FontWeight(weight = 900),
                  letterSpacing = TextUnit(value = 0.4f, TextUnitType.Sp))

              Spacer(modifier = Modifier.height(MaterialTheme.dimens.newEpisodeTitleTopSpace))

              CustomTextView(
                  modifier = Modifier.padding(start = MaterialTheme.dimens.channelIconStartSpace).testTag(TestTags.NEW_EPISODE_TITLE),
                  text = stringResource(id = R.string.new_episode),
                  textSize = MaterialTheme.typography.bodyLarge.fontSize,
                  textColor = colorResource(id = R.color.grey_secondary),
                  fontFamily = AppsFontUtils.fontBold,
                  fontWeight = FontWeight(weight = 800),
                  letterSpacing = TextUnit(value = 0.4f, TextUnitType.Sp))

              Spacer(modifier = Modifier.height(20.dp))
            }
            item {
              Column(
                  modifier =
                      Modifier.fillMaxWidth()
                          .wrapContentHeight()
                          .testTag(TestTags.EPISODE_SECTION)) {
                    SetRowItems(list = viewModel.mapEpisodeResponse(episodes))
                    Spacer(modifier = Modifier.height(20.dp))
                  }
            }
            item {
              Spacer(modifier = Modifier.height(30.dp))
              Divider()
            }
            item {
              Column(modifier = Modifier.fillMaxSize().testTag(TestTags.SERIES_OR_COURSE_SECTION)) {
                channel?.data?.channels?.forEach {
                  it?.let { it1 ->
                        viewModel.mapWholeChannelResponse(
                            channel = it1,
                            numOfEpisodes =
                                if (it.series.isNotEmpty()) it.series.size.toString()
                                else it.latestMedia.size.toString())
                      }
                      ?.let { it2 -> SetCourseOrSeriesItems(list = it2) }
                }
              }
            }

            item {
              CustomTextView(
                  modifier =
                      Modifier.padding(start = MaterialTheme.dimens.channelIconStartSpace).testTag(TestTags.BROWSE_CATEGORY_TITLE),
                  text = stringResource(id = R.string.browse_by_categories),
                  textSize = MaterialTheme.typography.bodyLarge.fontSize,
                  textColor = colorResource(id = R.color.grey_secondary),
                  fontFamily = AppsFontUtils.fontBold,
                  fontWeight = FontWeight(weight = 800),
                  letterSpacing = TextUnit(value = 0.4f, TextUnitType.Sp))

              Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingBetweenItems))
              Column(
                  modifier =
                      Modifier.fillMaxWidth()
                          .wrapContentHeight()
                          .testTag(TestTags.CATEGORY_SECTION)) {
                    categories?.categories?.let {
                      SetCategoryItems(viewModel.mapCategoriesResponse(it))
                    }
                  }
              Spacer(modifier = Modifier.height(30.dp))
            }
          }
        }
  }
}

@Composable
fun CheckErrorStates(viewModel: ChannelScreenViewModel) {
  when {
    viewModel.state.value.error.isNotEmpty() -> {
      // Handle error state
      Log.d("Error: ", viewModel.state.value.error)
      ErrorScreen(errorMessage = viewModel.state.value.error)
    }
    viewModel.episodeState.value.error.isNotEmpty() -> {
      // Handle error state
      Log.d("Error: ", viewModel.episodeState.value.error)
      ErrorScreen(errorMessage = viewModel.episodeState.value.error)
    }
    viewModel.categoriesState.value.error.isNotEmpty() -> {
      // Handle error state
      Log.d("Error: ", viewModel.categoriesState.value.error)
      ErrorScreen(errorMessage = viewModel.categoriesState.value.error)
    }
  }
}
