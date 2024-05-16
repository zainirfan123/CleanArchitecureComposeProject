package com.example.mindsvalleyapplication.feature_channels.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mindsvalleyapplication.feature_channels.domain.model.ChannelsResponseModel

@Dao
interface ChannelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(channelsResponseModel: ChannelsResponseModel)

    @Query("SELECT * FROM channel_data")
    suspend fun getAllChannels(): ChannelsResponseModel?
}