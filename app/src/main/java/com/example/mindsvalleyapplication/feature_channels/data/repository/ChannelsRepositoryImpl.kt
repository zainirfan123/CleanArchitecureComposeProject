package com.example.mindsvalleyapplication.feature_channels.data.repository
import com.example.mindsvalleyapplication.feature_channels.data.data_source.ApiService
import com.example.mindsvalleyapplication.feature_channels.data.data_source.ChannelsDatabase
import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.ChannelsResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.EpisodesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.repository.ChannelsRepository
import javax.inject.Inject

class ChannelsRepositoryImpl @Inject constructor(private val api: ApiService,private val channelsDatabase: ChannelsDatabase
) : ChannelsRepository {

  override suspend fun getChannels(isFetchedFromRoom:Boolean): ChannelsResponseModel {
    return if (isFetchedFromRoom) {
      // Fetch channels from Room
      return channelsDatabase.channelDao().getAllChannels()!!
    } else {
      channelsDatabase.channelDao().insert(api.getChannels())
      return api.getChannels()
    }
  }

  override suspend fun getCategories(isFetchedFromRoom: Boolean): CategoriesResponseModel {
    return api.getCategories()
  }

  override suspend fun getEpisodes(isFetchedFromRoom:Boolean): EpisodesResponseModel {
    return api.getNewEpisodes()
  }
}
