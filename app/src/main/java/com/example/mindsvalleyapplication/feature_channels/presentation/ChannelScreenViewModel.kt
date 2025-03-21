package com.example.mindsvalleyapplication.feature_channels.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mindsvalleyapplication.feature_channels.common.Constants
import com.example.mindsvalleyapplication.feature_channels.common.Resource
import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.ChannelsResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.CustomizeChannelResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.EpisodesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.GenericRowItemModel
import com.example.mindsvalleyapplication.feature_channels.domain.use_case.CategoriesUseCase
import com.example.mindsvalleyapplication.feature_channels.domain.use_case.ChannelsUseCase
import com.example.mindsvalleyapplication.feature_channels.domain.use_case.EpisodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.SelectInstance

@HiltViewModel
class ChannelScreenViewModel
@Inject
constructor(
    private val channelUseCase: ChannelsUseCase,
    private val episodesUseCase: EpisodesUseCase,
    private val categoriesUseCase: CategoriesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
  private val _state = mutableStateOf(ChannelViewState())
  val state: State<ChannelViewState> = _state

  private val _episodeState = mutableStateOf(EpisodeViewState())
  val episodeState: State<EpisodeViewState> = _episodeState

  private val _categoriesState = mutableStateOf(CategoriesViewState())
  val categoriesState: State<CategoriesViewState> = _categoriesState
  init {
      savedStateHandle.get<Boolean>(Constants.PARAM_IS_FETCH_FROM_DB)?.let {isFetchFromDB->
          fetchAllContent(isFetchFromDB)
      }
  }

  fun handleScreenEvent(isFetchedFromRoom: Boolean, event: ChannelScreenEvents) {
    when (event) {
      is ChannelScreenEvents.Refresh -> {
        // Call your API here
        fetchAllContent(isFetchedFromRoom)
      }
    // Handle more screen events if needed
    }
  }

  fun callChannelApi(isFetchedFromRoom: Boolean) {
    viewModelScope.launch {
      channelUseCase.invoke(isFetchedFromRoom).collect { result ->
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

  fun callEpisodeApi(isFetchedFromRoom: Boolean) {
    viewModelScope.launch {
      episodesUseCase.invoke(isFetchedFromRoom).collect { result ->
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

 fun callCategoriesApi(isFetchedFromRoom: Boolean) {
    viewModelScope.launch {
      categoriesUseCase.invoke(isFetchedFromRoom).collect { result ->
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

  fun getSeriesOrCourseList(data: ChannelsResponseModel.Data.Channel): List<GenericRowItemModel> {
    /* This method helps to map series or course. */
    val list = arrayListOf<GenericRowItemModel>()
    if (data.series.isNotEmpty()) {
      data.series.map {
        list.add(GenericRowItemModel(title = it.title, subTitle = "", image = it.coverAsset.url, isSeries = true))
      }
    } else {
      data.latestMedia.map {
        list.add(GenericRowItemModel(title = it.title, subTitle = "", image = it.coverAsset.url, isSeries = false))
      }
    }
    return list
  }

  fun mapEpisodeResponse(data: EpisodesResponseModel.Data?): List<GenericRowItemModel> {
    /* This method help map episode response */
    val episodes = data?.media
    val list = arrayListOf<GenericRowItemModel>()
    if (!episodes.isNullOrEmpty()) {
      episodes.map {
        list.add(
            GenericRowItemModel(
                title = it?.channel?.title ?: "",
                subTitle = it?.title ?: "",
                image = it?.coverAsset?.url ?: "",
                isSeries = false))
      }
    }
    return list
  }

  fun mapWholeChannelResponse(
      channel: ChannelsResponseModel.Data.Channel,
      numOfEpisodes: String
  ): List<CustomizeChannelResponseModel> {
    /* This method help map whole channel response. */
    val channelsList =
        mutableListOf<CustomizeChannelResponseModel>().also {
          it.add(
              CustomizeChannelResponseModel(
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
    /* This method help to create pair of categories */
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

  private fun fetchAllContent(isFetchedFromRoom: Boolean) {
    /* This method helps to fetch all data either from server or local db */
    callEpisodeApi(isFetchedFromRoom = isFetchedFromRoom)
    callChannelApi(isFetchedFromRoom = isFetchedFromRoom)
    callCategoriesApi(isFetchedFromRoom = isFetchedFromRoom)
  }
}
