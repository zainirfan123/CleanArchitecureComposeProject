package com.example.mindsvalleyapplication.feature_channels.data.repository
import com.example.mindsvalleyapplication.feature_channels.data.data_source.ApiService
import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.ChannelsResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.EpisodesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.repository.ChannelsRepository
import javax.inject.Inject

class ChannelsRepositoryImpl @Inject constructor(private val api: ApiService) : ChannelsRepository {

  override suspend fun getChannels(): ChannelsResponseModel {
    return api.getChannels()
  }

  override suspend fun getCategories(): CategoriesResponseModel {
    return api.getCategories()
  }

  override suspend fun getEpisodes(): EpisodesResponseModel {
    return api.getNewEpisodes()
  }
}
