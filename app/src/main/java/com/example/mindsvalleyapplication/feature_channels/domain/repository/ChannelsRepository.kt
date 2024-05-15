package com.example.mindsvalleyapplication.feature_channels.domain.repository

import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.ChannelsResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.EpisodesResponseModel


interface ChannelsRepository {

  suspend fun getChannels(isFetchedFromRoom:Boolean): ChannelsResponseModel
  suspend fun getCategories(isFetchedFromRoom:Boolean): CategoriesResponseModel
  suspend fun getEpisodes(isFetchedFromRoom:Boolean): EpisodesResponseModel
}
