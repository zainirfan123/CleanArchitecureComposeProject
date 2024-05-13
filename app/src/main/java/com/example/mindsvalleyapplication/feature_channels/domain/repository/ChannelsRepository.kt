package com.example.mindsvalleyapplication.feature_channels.domain.repository

import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.ChannelsResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.EpisodesResponseModel


interface ChannelsRepository {

  suspend fun getChannels(): ChannelsResponseModel
  suspend fun getCategories(): CategoriesResponseModel
  suspend fun getEpisodes(): EpisodesResponseModel
}
