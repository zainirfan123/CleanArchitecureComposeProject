package com.example.mindsvalleyapplication.feature_channels.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mindsvalleyapplication.feature_channels.data.data_source.local.RoomTypeConverter

@Entity(tableName = "episodes")
data class EpisodesResponseModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @TypeConverters(RoomTypeConverter::class) val data: Data
) {

  data class Data(val media: List<Media?>? = emptyList()) {
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
