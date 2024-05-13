package com.example.mindsvalleyapplication.feature_channels.domain.model

data class EpisodesResponseModel(val data: Data) {

  data class Data(val media: List<Media>) {
    data class Media(
        val channel: Channel,
        val coverAsset: CoverAsset,
        val title: String,
        val type: String
    ) {
      data class Channel(val title: String)
    }
  }
}
