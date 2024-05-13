package com.example.mindsvalleyapplication.feature_channels.domain.model

data class ChannelsResponseModel(val data: Data) {
  data class Data(val channels: List<Channel>) {
    data class Channel(
        val coverAsset: CoverAsset,
        val iconAsset: IconAsset,
        val id: String,
        val latestMedia: List<LatestMedia>,
        val mediaCount: Int,
        val series: List<Series>,
        val slug: String,
        val title: String
    ) {
      data class IconAsset(val thumbnailUrl: String, val url: String)

      data class LatestMedia(val coverAsset: CoverAsset, val title: String, val type: String)

      data class Series(val coverAsset: CoverAsset, val id: String, val title: String)
    }
  }
}
