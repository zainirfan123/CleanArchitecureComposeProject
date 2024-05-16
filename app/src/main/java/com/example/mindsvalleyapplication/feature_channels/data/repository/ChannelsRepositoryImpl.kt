package com.example.mindsvalleyapplication.feature_channels.data.repository

import com.example.mindsvalleyapplication.feature_channels.data.data_source.ApiService
import com.example.mindsvalleyapplication.feature_channels.data.data_source.ChannelsDatabase
import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.ChannelsResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.EpisodesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.repository.ChannelsRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChannelsRepositoryImpl
@Inject
constructor(private val api: ApiService, private val channelsDatabase: ChannelsDatabase) :
    ChannelsRepository {

  override suspend fun getChannels(isFetchedFromRoom: Boolean): ChannelsResponseModel {
    return if (isFetchedFromRoom) {
      // Fetch channels from Room
      withContext(Dispatchers.IO) {
        channelsDatabase.channelDao().getAllChannels()
            ?: ChannelsResponseModel(0, ChannelsResponseModel.Data(emptyList()))
      }
    } else {
      channelsDatabase.channelDao().insert(api.getChannels())
      api.getChannels()
    }
  }

  override suspend fun getCategories(isFetchedFromRoom: Boolean): CategoriesResponseModel {
    return if (isFetchedFromRoom) {
      // Fetch categories from Room
      withContext(Dispatchers.IO) {
        channelsDatabase.categoryDao().getAllCategories()
            ?: CategoriesResponseModel(0, CategoriesResponseModel.Data(emptyList()))
      }
    } else {
      channelsDatabase.categoryDao().insertCategories(api.getCategories())
      api.getCategories()
    }
  }

  override suspend fun getEpisodes(isFetchedFromRoom: Boolean): EpisodesResponseModel {
    return if (isFetchedFromRoom) {
      // Fetch episodes from Room
      withContext(Dispatchers.IO) {
        channelsDatabase.episodeDao().getAllEpisodes()
            ?: EpisodesResponseModel(0, EpisodesResponseModel.Data(emptyList()))
      }
    } else {
      channelsDatabase.episodeDao().insertEpisodes(api.getNewEpisodes())
      api.getNewEpisodes()
    }
  }
}
