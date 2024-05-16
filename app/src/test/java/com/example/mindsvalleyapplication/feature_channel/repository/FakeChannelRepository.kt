package com.example.mindsvalleyapplication.feature_channel.repository

import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.ChannelsResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.CoverAsset
import com.example.mindsvalleyapplication.feature_channels.domain.model.EpisodesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.repository.ChannelsRepository

class FakeChannelRepository : ChannelsRepository {

   val coverAsset = ChannelsResponseModel.Data.Channel.CoverAsset("")
   val iconAsset = ChannelsResponseModel.Data.Channel.IconAsset("", "")
  private var latestMediaList = arrayListOf<ChannelsResponseModel.Data.Channel.LatestMedia>()
  private var seriesList = arrayListOf<ChannelsResponseModel.Data.Channel.Series>()

  private val channels =
      ChannelsResponseModel.Data.Channel(
          coverAsset = coverAsset,
          iconAsset = iconAsset,
          id = "",
          latestMedia = getDummyLatestMediaList(),
          series = getDummySeriesList(),
          slug = "",
          title = "",
          mediaCount = 0)
  private val categories = CategoriesResponseModel.Data.Category("")
  private val episodes = EpisodesResponseModel.Data.Media(channel=EpisodesResponseModel.Data.Media.Channel(title = ""), coverAsset = CoverAsset(url=""),"","")
  val channelResponseModel =
      ChannelsResponseModel(id = 0, ChannelsResponseModel.Data(listOf(channels)))
  val episodeResponseModel = EpisodesResponseModel(id = 0, EpisodesResponseModel.Data(listOf(episodes)))

  val categoryResponseModel =
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

   fun getDummyLatestMediaList(): List<ChannelsResponseModel.Data.Channel.LatestMedia> {
    latestMediaList.add(
        ChannelsResponseModel.Data.Channel.LatestMedia(
            coverAsset = coverAsset, title = "media", type = ""))
    return latestMediaList
  }

   fun getDummySeriesList(): List<ChannelsResponseModel.Data.Channel.Series> {
    seriesList.add(
        ChannelsResponseModel.Data.Channel.Series(coverAsset = coverAsset, title = "series", id = "0"))
    return seriesList
  }
}
