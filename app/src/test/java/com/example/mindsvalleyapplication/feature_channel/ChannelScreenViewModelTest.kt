package com.example.mindsvalleyapplication.feature_channel

import com.example.mindsvalleyapplication.feature_channel.repository.FakeChannelRepository
import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.CoverAsset
import com.example.mindsvalleyapplication.feature_channels.domain.model.EpisodesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.GenericRowItemModel
import com.example.mindsvalleyapplication.feature_channels.domain.use_case.CategoriesUseCase
import com.example.mindsvalleyapplication.feature_channels.domain.use_case.ChannelsUseCase
import com.example.mindsvalleyapplication.feature_channels.domain.use_case.EpisodesUseCase
import com.example.mindsvalleyapplication.feature_channels.presentation.ChannelScreenViewModel
import com.example.testproject2024.coroutine_rule.MainDispatcherRule
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ChannelScreenViewModelTest {

  @get:Rule val mainDispatcherRule = MainDispatcherRule()

  private lateinit var fakeChannelRepository: FakeChannelRepository

  private lateinit var viewModel: ChannelScreenViewModel

  @Before
  fun setUp() {
    fakeChannelRepository = FakeChannelRepository()
    viewModel =
        ChannelScreenViewModel(
            channelUseCase = ChannelsUseCase(fakeChannelRepository),
            episodesUseCase = EpisodesUseCase(fakeChannelRepository),
            categoriesUseCase = CategoriesUseCase(fakeChannelRepository))
  }

  @Test
  fun `test success scenario of channel api`() = runTest {
    // Mock successful response
    val channelResponse = fakeChannelRepository.channelResponseModel
    // Trigger API call
    viewModel.callCategoriesApi(true)

    // Verify state
    assertEquals(viewModel.state.value.response, channelResponse)
  }

  @Test
  fun `test success scenario of episode api`() = runTest {
    // Mock successful response
    val episodeResponse = fakeChannelRepository.episodeResponseModel
    // Trigger API call
    viewModel.callEpisodeApi(true)

    // Verify state
    assertEquals(viewModel.episodeState.value.response, episodeResponse)
  }

  @Test
  fun `test success scenario of category api`() = runTest {
    // Mock successful response
    val categoryResponse = fakeChannelRepository.categoryResponseModel
    // Trigger API call
    viewModel.callCategoriesApi(true)

    // Verify state
    assertEquals(viewModel.categoriesState.value.response, categoryResponse)
  }

  @Test
  fun `test channels series list comes from server`() = runBlocking{
    // Mock successful response
    val channelResponse = fakeChannelRepository.channelResponseModel
    // Trigger API call
    viewModel.callChannelApi(true)

    val hasSeries = channelResponse.data.channels?.any { channel ->
      // Your condition to check if the promotion has an image
      // For example, assuming promotion has an image URL
      channel?.series != null
    }

    // Assert that at least one promotion has an image
    TestCase.assertTrue(hasSeries ?: false)
  }

  @Test
  fun `test channels latest media list comes from server`() = runBlocking{
    // Mock successful response
    val channelResponse = fakeChannelRepository.channelResponseModel
    // Trigger API call
    viewModel.callChannelApi(true)

    val hasLatestMedia = channelResponse.data.channels?.any { channel ->
      // Your condition to check if the promotion has an image
      // For example, assuming promotion has an image URL
      channel?.latestMedia != null
    }

    // Assert that at least one promotion has an image
    TestCase.assertTrue(hasLatestMedia ?: false)
  }


  @Test
  fun `mapCategoriesResponse maps list correctly`() {
    // Given
    val inputList = listOf(
      CategoriesResponseModel.Data.Category("Category1"),
      CategoriesResponseModel.Data.Category("Category2"),
      CategoriesResponseModel.Data.Category("Category3"),
      CategoriesResponseModel.Data.Category("Category4")
    )

    // When
    val result = viewModel.mapCategoriesResponse(inputList)

    // Then
    val expectedList = listOf(
      Pair("Category1", "Category2"),
      Pair("Category3", "Category4")
    )
    assertEquals(expectedList, result) }

  @Test
  fun `mapCategoriesResponse handles odd-sized list`() {
    // Given
    val inputList = listOf(
      CategoriesResponseModel.Data.Category("Category1"),
      CategoriesResponseModel.Data.Category("Category2"),
      CategoriesResponseModel.Data.Category("Category3")
    )

    // When
    val result = viewModel.mapCategoriesResponse(inputList)

    // Then
    val expectedList = listOf(
      Pair("Category1", "Category2"),
      Pair("Category3", null)
    )
    assertEquals(expectedList, result)
  }

  @Test
  fun `mapCategoriesResponse handles null input`() {
    // Given
    val inputList: List<CategoriesResponseModel.Data.Category>? = null

    // When
    val result = viewModel.mapCategoriesResponse(inputList)

    // Then
    assertEquals(emptyList<Pair<String, String?>>(), result)
  }

  @Test
  fun `mapCategoriesResponse handles empty input`() {
    // Given

    val inputList = emptyList<CategoriesResponseModel.Data.Category>()

    // When
    val result = viewModel.mapCategoriesResponse(inputList)

    // Then
    assertEquals(emptyList<Pair<String, String?>>(), result)
  }


  @Test
  fun `mapEpisodeResponse maps data correctly`() {
    // Given
    val episode1 = EpisodesResponseModel.Data.Media(
      channel = EpisodesResponseModel.Data.Media.Channel(title = "Channel 1"),
      title = "Episode 1",
      coverAsset = CoverAsset(url = "https://example.com/episode1.jpg"),
      type = "type 1"
    )
    val episode2 = EpisodesResponseModel.Data.Media(
      channel = EpisodesResponseModel.Data.Media.Channel(title = "Channel 2"),
      title = "Episode 2",
      coverAsset = CoverAsset(url = "https://example.com/episode2.jpg"),
      type = "type 2"
    )
    val data = EpisodesResponseModel.Data(listOf(episode1, episode2))

    // When
    val result = viewModel.mapEpisodeResponse(data)

    // Then
    val expectedList = listOf(
      GenericRowItemModel(subTitle = "Channel 1", title = "Episode 1", image = "https://example.com/episode1.jpg"),
      GenericRowItemModel(subTitle = "Channel 2", title =  "Episode 2", image =  "https://example.com/episode2.jpg")
    )
    assertEquals(expectedList, result)
  }

  @Test
  fun `mapEpisodeResponse handles null data`() {
    // Given
    val data: EpisodesResponseModel.Data? = null

    // When
    val result = viewModel.mapEpisodeResponse(data)

    // Then
    assertEquals(emptyList<GenericRowItemModel>(), result)
  }

  @Test
  fun `mapEpisodeResponse handles empty media list`() {
    // Given
    val data = EpisodesResponseModel.Data(emptyList())

    // When
    val result = viewModel.mapEpisodeResponse(data)

    // Then
    assertEquals(emptyList<GenericRowItemModel>(), result)
  }

}
