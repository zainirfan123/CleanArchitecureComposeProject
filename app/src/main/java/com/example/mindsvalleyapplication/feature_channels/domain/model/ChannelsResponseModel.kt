package com.example.mindsvalleyapplication.feature_channels.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mindsvalleyapplication.feature_channels.data.data_source.local.RoomTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "channel_data")
data class ChannelsResponseModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @TypeConverters(RoomTypeConverter::class) val data: Data
) {
    data class Data(
        @SerializedName("channels") val channels: List<Channel?>? = emptyList()
    ) {
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
            data class CoverAsset(val url: String)
            data class IconAsset(val thumbnailUrl: String, val url: String)
            data class LatestMedia(val coverAsset: CoverAsset, val title: String, val type: String)
            data class Series(val coverAsset: CoverAsset, val id: String, val title: String)
        }
    }
}