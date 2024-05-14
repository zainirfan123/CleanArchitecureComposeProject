package com.example.mindsvalleyapplication.feature_channels.presentation.channels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mindsvalleyapplication.feature_channels.common.Resource
import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.ChannelsResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.CustomizeResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.EpisodesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.HorizontalItemModel
import com.example.mindsvalleyapplication.feature_channels.domain.use_case.CategoriesUseCase
import com.example.mindsvalleyapplication.feature_channels.domain.use_case.ChannelsUseCase
import com.example.mindsvalleyapplication.feature_channels.domain.use_case.EpisodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ChannelScreenViewModel
@Inject
constructor(
    private val channelUseCase: ChannelsUseCase,
    private val episodesUseCase: EpisodesUseCase,
    private val categoriesUseCase: CategoriesUseCase
) : ViewModel() {
  private val _state = mutableStateOf(ChannelViewState())
  val state: State<ChannelViewState> = _state

  private val _episodeState = mutableStateOf(EpisodeViewState())
  val episodeState: State<EpisodeViewState> = _episodeState

  private val _categoriesState = mutableStateOf(CategoriesViewState())
  val categoriesState: State<CategoriesViewState> = _categoriesState

  init {
    callEpisodeApi()
    callChannelApi()
    callCategoriesApi()
  }

  fun callChannelApi() {
    viewModelScope.launch {
      channelUseCase.invoke().collect { result ->
        when (result) {
          is Resource.Loading -> {
            _state.value = ChannelViewState(isLoading = true)
          }
          is Resource.Success -> {
            _state.value = ChannelViewState(response = result.data)
          }
          is Resource.Error -> {
            _state.value =
                ChannelViewState(error = result.message ?: "An Unexpected error occurred.")
          }
        }
      }
    }
  }

  fun callEpisodeApi() {
    viewModelScope.launch {
      episodesUseCase.invoke().collect { result ->
        when (result) {
          is Resource.Loading -> {
            _episodeState.value = EpisodeViewState(isLoading = true)
          }
          is Resource.Success -> {
            result.data?.let { _episodeState.value = EpisodeViewState(response = it) }
          }
          is Resource.Error -> {
            _episodeState.value =
                EpisodeViewState(error = result.message ?: "An Unexpected error occurred.")
          }
        }
      }
    }
  }

  fun callCategoriesApi() {
    viewModelScope.launch {
      categoriesUseCase.invoke().collect { result ->
        when (result) {
          is Resource.Loading -> {
            _categoriesState.value = CategoriesViewState(isLoading = true)
          }
          is Resource.Success -> {
            _categoriesState.value = CategoriesViewState(response = result.data)
          }
          is Resource.Error -> {
            _categoriesState.value =
                CategoriesViewState(error = result.message ?: "An Unexpected error occurred.")
          }
        }
      }
    }
  }

  private fun getSeriesOrCourseList(
      data: ChannelsResponseModel.Data.Channel
  ): List<HorizontalItemModel> {
    val list = arrayListOf<HorizontalItemModel>()
    if (data.series.isNotEmpty()) {
      data.series.map {
        list.add(HorizontalItemModel(title = it.title, subTitle = "", image = it.coverAsset.url))
      }
    } else {
      data.latestMedia.map {
        list.add(HorizontalItemModel(title = it.title, subTitle = "", image = it.coverAsset.url))
      }
    }
    return list
  }

  fun mapEpisodeResponse(data: EpisodesResponseModel.Data?): List<HorizontalItemModel> {
    val episodes = data?.media
    val list = arrayListOf<HorizontalItemModel>()
    if (!episodes.isNullOrEmpty()) {
      episodes.map {
        list.add(
            HorizontalItemModel(
                title = it.channel.title, subTitle = it.title, image = it.coverAsset.url))
      }
    }
    return list
  }

  fun mapWholeChannelResponse(
      channel: ChannelsResponseModel.Data.Channel,
      numOfEpisodes: String
  ): List<CustomizeResponseModel> {
    val channelsList =
        mutableListOf<CustomizeResponseModel>().also {
          it.add(
              CustomizeResponseModel(
                  title = channel.title,
                      numOfEpisodes =
                          if (numOfEpisodes.toInt() == 1) {
                            "$numOfEpisodes episode"
                          } else "$numOfEpisodes episodes",
                  slug = channel.slug,
                  items = getSeriesOrCourseList(channel)))
        }
    return channelsList
  }

  fun mapCategoriesResponse(
      list: List<CategoriesResponseModel.Data.Category>?
  ): List<Pair<String, String?>> {
    val pairs = mutableListOf<Pair<String, String?>>()
    list?.let {
      for (i in it.indices step 2) {
        if (i + 1 < it.size) {
          pairs.add(Pair(it[i].name, it[i + 1].name))
        } else {
          pairs.add(Pair(it[i].name, null))
        }
      }
    }
    return pairs
  }
}
