package com.example.mindsvalleyapplication.feature_channel.presentation

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mindsvalleyapplication.di.AppModule
import com.example.mindsvalleyapplication.feature_channel.repository.FakeChannelRepository
import com.example.mindsvalleyapplication.feature_channels.domain.use_case.CategoriesUseCase
import com.example.mindsvalleyapplication.feature_channels.domain.use_case.ChannelsUseCase
import com.example.mindsvalleyapplication.feature_channels.domain.use_case.EpisodesUseCase
import com.example.mindsvalleyapplication.feature_channels.presentation.ChannelScreen
import com.example.mindsvalleyapplication.feature_channels.presentation.ChannelScreenViewModel
import com.example.mindsvalleyapplication.feature_channels.presentation.main.MainActivity
import com.example.mindsvalleyapplication.feature_channels.presentation.util.Screen
import com.example.mindsvalleyapplication.ui.theme.MindsValleyApplicationTheme
import com.example.mindsvalleyapplication.utils.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class ChannelScreenTest {

  @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)

  @get:Rule(order = 1) val composeRule = createAndroidComposeRule<MainActivity>()

  private lateinit var fakeChannelRepository: FakeChannelRepository

  private lateinit var viewModel: ChannelScreenViewModel

  @Before
  fun setUp() {
    hiltRule.inject()
    fakeChannelRepository = FakeChannelRepository()
    viewModel =
        ChannelScreenViewModel(
            channelUseCase = ChannelsUseCase(fakeChannelRepository),
            episodesUseCase = EpisodesUseCase(fakeChannelRepository),
            categoriesUseCase = CategoriesUseCase(fakeChannelRepository))

    composeRule.activity.setContent {
      val navController = rememberNavController()
      MindsValleyApplicationTheme {
        NavHost(navController = navController, startDestination = Screen.Channels.route) {
          composable(route = Screen.Channels.route) {
            ChannelScreen(navController = navController, viewModel)
          }
        }
      }
    }
  }

  @Test
  fun channelAppTitle_isVisible() {
    composeRule.onNodeWithTag(TestTags.CHANNEL_APP_TITLE).assertExists()
  }

  @Test
  fun episodeAppTitle_isVisible() {
    composeRule.onNodeWithTag(TestTags.NEW_EPISODE_TITLE).assertExists()
  }

  @Test
  fun checkEpisodeSection_isVisible() {
    // Update the ViewModel state
    composeRule.onNodeWithTag(TestTags.EPISODE_SECTION).assertIsDisplayed()
  }

  @Test
  fun checkSeriesOrCourseSection_isVisible() {
    // Update the ViewModel state
    composeRule.onNodeWithTag(TestTags.SERIES_OR_COURSE_SECTION).assertIsDisplayed()
  }

  @Test
  fun checkBrowseCategoryTitle_isVisible() {
    // Update the ViewModel state
    composeRule.onNodeWithTag(TestTags.BROWSE_CATEGORY_TITLE).assertIsDisplayed()
  }

  @Test
  fun checkCategorySection_isVisible() {
    // Update the ViewModel state
    composeRule.onNodeWithTag(TestTags.CATEGORY_SECTION).assertIsDisplayed()
  }

}
