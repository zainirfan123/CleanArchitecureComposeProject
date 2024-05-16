package com.example.mindsvalleyapplication.feature_channel.repository

import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.ChannelsResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.EpisodesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.repository.ChannelsRepository

class FakeChannelRepository : ChannelsRepository {

  val categoryResponseModel =
      CategoriesResponseModel(id = 0, CategoriesResponseModel.Data(listOf()))
  val channelResponseModel = ChannelsResponseModel(id = 0, ChannelsResponseModel.Data(listOf()))
  val episodeResponseModel = EpisodesResponseModel(id = 0, EpisodesResponseModel.Data(listOf()))

  override suspend fun getChannels(isFetchedFromRoom: Boolean): ChannelsResponseModel {
    return channelResponseModel
  }

  override suspend fun getCategories(isFetchedFromRoom: Boolean): CategoriesResponseModel {
    return categoryResponseModel
  }

  override suspend fun getEpisodes(isFetchedFromRoom: Boolean): EpisodesResponseModel {
    return episodeResponseModel
  }
}
