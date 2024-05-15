package com.example.mindsvalleyapplication.feature_channels.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mindsvalleyapplication.feature_channels.domain.model.ChannelsEntity
import com.example.mindsvalleyapplication.feature_channels.domain.model.ChannelsResponseModel

@Database(entities = [ChannelsResponseModel::class], version = 3)
@TypeConverters(RoomTypeConverter::class)
abstract class ChannelsDatabase : RoomDatabase() {
    abstract fun channelDao(): ChannelDao
}
