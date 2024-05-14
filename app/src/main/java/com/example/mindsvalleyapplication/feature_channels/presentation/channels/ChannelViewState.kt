package com.example.mindsvalleyapplication.feature_channels.presentation.channels

import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.ChannelsResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.EpisodesResponseModel

data class ChannelViewState(
    val isLoading: Boolean = false,
    val response: ChannelsResponseModel? = null,
    val error: String = ""
)
data class EpisodeViewState(
    val isLoading: Boolean = false,
    val response: EpisodesResponseModel? = null,
    val error: String = ""
)
data class CategoriesViewState(
    val isLoading: Boolean = false,
    val response: CategoriesResponseModel? = null,
    val error: String = ""
)