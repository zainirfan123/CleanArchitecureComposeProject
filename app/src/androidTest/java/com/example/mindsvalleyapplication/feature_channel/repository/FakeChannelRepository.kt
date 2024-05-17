package com.example.mindsvalleyapplication.feature_channel.repository

import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.ChannelsResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.CoverAsset
import com.example.mindsvalleyapplication.feature_channels.domain.model.EpisodesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.repository.ChannelsRepository

class FakeChannelRepository : ChannelsRepository {

  private val coverAsset =
      ChannelsResponseModel.Data.Channel.CoverAsset(
          "https://assets.mindvalley.com/api/v1/assets/5bdbdd0e-3bd3-432b-b8cb-3d3556c58c94.jpg?transform=w_1080")
  private val iconAsset =
      ChannelsResponseModel.Data.Channel.IconAsset(
          "",
          "https://assets.mindvalley.com/api/v1/assets/eb493421-c048-492b-a471-bed7604e95d6.jpg?transform=w_1080")
  private var latestMediaList = arrayListOf<ChannelsResponseModel.Data.Channel.LatestMedia>()
  private var seriesList = arrayListOf<ChannelsResponseModel.Data.Channel.Series>()

  private val channels =
      ChannelsResponseModel.Data.Channel(
          coverAsset = coverAsset,
          iconAsset = iconAsset,
          id = "0",
          latestMedia = getDummyLatestMediaList(),
          series = getDummySeriesList(),
          slug = "slug",
          title = "Channel 1",
          mediaCount = 0)
  private val categories = CategoriesResponseModel.Data.Category("Category A")
  private val episodes =
      EpisodesResponseModel.Data.Media(
          channel = EpisodesResponseModel.Data.Media.Channel(title = "Channel 1"),
          coverAsset =
              CoverAsset(
                  url =
                      "https://assets.mindvalley.com/api/v1/assets/5bdbdd0e-3bd3-432b-b8cb-3d3556c58c94.jpg?transform=w_1080"),
          "Episode 1",
          "course")
  private val channelResponseModel =
      ChannelsResponseModel(id = 0, ChannelsResponseModel.Data(listOf(channels)))
  private val episodeResponseModel =
      EpisodesResponseModel(id = 0, EpisodesResponseModel.Data(listOf(episodes)))

  private val categoryResponseModel =
      CategoriesResponseModel(id = 0, CategoriesResponseModel.Data(listOf(categories)))

  override suspend fun getChannels(isFetchedFromRoom: Boolean): ChannelsResponseModel {
    return channelResponseModel
  }

  override suspend fun getCategories(isFetchedFromRoom: Boolean): CategoriesResponseModel {
    return categoryResponseModel
  }

  override suspend fun getEpisodes(isFetchedFromRoom: Boolean): EpisodesResponseModel {
    return episodeResponseModel
  }

  private fun getDummyLatestMediaList(): List<ChannelsResponseModel.Data.Channel.LatestMedia> {
    latestMediaList.add(
        ChannelsResponseModel.Data.Channel.LatestMedia(
            coverAsset = coverAsset, title = "media", type = "course"))
    return latestMediaList
  }

  private fun getDummySeriesList(): List<ChannelsResponseModel.Data.Channel.Series> {
    seriesList.add(
        ChannelsResponseModel.Data.Channel.Series(
            coverAsset = coverAsset, title = "series", id = "0"))
    return seriesList
  }
}
