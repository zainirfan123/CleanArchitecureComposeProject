package com.example.mindsvalleyapplication.feature_channels.presentation.channels

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mindsvalleyapplication.R
import com.example.mindsvalleyapplication.feature_channels.presentation.helper.AppsFontUtils
import com.example.mindsvalleyapplication.feature_channels.presentation.helper.ComposeUtils.CustomTextView
import com.example.mindsvalleyapplication.feature_channels.presentation.helper.ComposeUtils.ErrorScreen
import com.example.mindsvalleyapplication.feature_channels.presentation.helper.ComposeUtils.Divider
import com.example.mindsvalleyapplication.feature_channels.presentation.helper.ComposeUtils.SetChannels
import com.example.mindsvalleyapplication.feature_channels.presentation.helper.ComposeUtils.SetHorizontalItems
import com.example.mindsvalleyapplication.feature_channels.presentation.helper.ComposeUtils.ShowCategories
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ChannelScreen(
    navController: NavController,
    viewModel: ChannelScreenViewModel = hiltViewModel()
) {
  setUpChannelUI(viewModel = viewModel)
}

@Composable
private fun setUpChannelUI(viewModel: ChannelScreenViewModel) {
  val channel = viewModel.state.value.response
  val episodes = viewModel.episodeState.value.response?.data
  val categories = viewModel.categoriesState.value.response?.data

  Box(modifier = Modifier.fillMaxSize().background(colorResource(id = R.color.channel_bg))) {
    when {
      !viewModel.state.value.error.isNullOrEmpty() -> {
        // Handle error state
        Log.d("Error: ", viewModel.state.value.error)
        ErrorScreen(errorMessage = viewModel.state.value.error)
      }
      !viewModel.episodeState.value.error.isNullOrEmpty() -> {
        // Handle error state
        Log.d("Error: ", viewModel.episodeState.value.error)
        ErrorScreen(errorMessage = viewModel.episodeState.value.error)
      }
      !viewModel.categoriesState.value.error.isNullOrEmpty() -> {
        // Handle error state
        Log.d("Error: ", viewModel.categoriesState.value.error)
        ErrorScreen(errorMessage = viewModel.categoriesState.value.error)
      }
    }
    val swipeRefreshState =
        rememberSwipeRefreshState(
            isRefreshing =
                viewModel.state.value.isLoading ||
                    viewModel.episodeState.value.isLoading ||
                    viewModel.categoriesState.value.isLoading)
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
          viewModel.callChannelApi()
          viewModel.callEpisodeApi()
          viewModel.callCategoriesApi()
          // After fetching data, set isRefreshing to false
          // (either in ViewModel after successful API calls or after handling errors)
        }) {
          LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(5.dp)) {
            item {
              Spacer(modifier = Modifier.height(56.dp))
              CustomTextView(
                  modifier = Modifier.padding(start = 10.dp),
                  text = stringResource(id = R.string.channel_title),
                  textSize = 30,
                  textColor = colorResource(id = R.color.grey),
                  fontFamily = AppsFontUtils.fontBold,
                  fontWeight = FontWeight(weight = 900),
                  letterSpacing = TextUnit(value = 0.4f, TextUnitType.Sp))

              Spacer(modifier = Modifier.height(30.dp))

              CustomTextView(
                  modifier = Modifier.padding(start = 10.dp),
                  text = stringResource(id = R.string.new_episode),
                  textSize = 20,
                  textColor = colorResource(id = R.color.grey_secondary),
                  fontFamily = AppsFontUtils.fontBold,
                  fontWeight = FontWeight(weight = 800),
                  letterSpacing = TextUnit(value = 0.4f, TextUnitType.Sp))

              Spacer(modifier = Modifier.height(20.dp))
            }
            item { // new episodes
              SetHorizontalItems(list = viewModel.mapEpisodeResponse(episodes))
              Spacer(modifier = Modifier.height(20.dp))
            }
            item { Divider() }
            item {
              channel?.data?.channels?.forEach {
                SetChannels(
                    context = LocalContext.current,
                    list =
                        viewModel.mapWholeChannelResponse(
                            channel = it,
                            numOfEpisodes =
                                if (it.series.isNotEmpty()) it.series.size.toString()
                                else it.latestMedia.size.toString()))
              }
            }

            item {
                CustomTextView(
                    modifier = Modifier.padding(start = 10.dp),
                    text = stringResource(id = R.string.browse_by_categories),
                    textSize = 20,
                    textColor = colorResource(id = R.color.grey_secondary),
                    fontFamily = AppsFontUtils.fontBold,
                    fontWeight = FontWeight(weight = 800),
                    letterSpacing = TextUnit(value = 0.4f, TextUnitType.Sp))

                Spacer(modifier = Modifier.height(10.dp))
                ShowCategories(viewModel.mapCategoriesResponse(categories?.categories))
                Spacer(modifier = Modifier.height(30.dp))

            }
          }
        }
  }
}
