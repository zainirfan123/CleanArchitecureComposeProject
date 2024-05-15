package com.example.mindsvalleyapplication.feature_channels.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ChannelsEntity(
     val data: Data
) {
  data class Data(@SerializedName("channels") val channels: List<Channel>) {
    data class Channel(
        val coverAsset: CoverAsset,
        val iconAsset: IconAsset,
        val id: String,
        val latestMedia: LatestMediaList,
        val mediaCount: Int,
        val series: SeriesList,
        val slug: String,
        val title: String
    ) {
      data class IconAsset(val thumbnailUrl: String, val url: String)

      data class LatestMedia(val coverAsset: CoverAsset, val title: String, val type: String)

      data class Series(val coverAsset: CoverAsset, val id: String, val title: String)
    }
  }
}
