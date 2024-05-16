package com.example.mindsvalleyapplication.feature_channels.domain.model

data class CustomizeChannelResponseModel(
    val id: Int? = null,
    val title: String,
    val numOfEpisodes: String,
    val slug: String?,
    val items: List<GenericRowItemModel>
)
