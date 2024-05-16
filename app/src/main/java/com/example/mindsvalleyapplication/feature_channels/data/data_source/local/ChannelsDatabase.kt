package com.example.mindsvalleyapplication.feature_channels.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.ChannelsResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.EpisodesResponseModel

@Database(entities = [ChannelsResponseModel::class,EpisodesResponseModel::class,CategoriesResponseModel::class], version = 4)
@TypeConverters(RoomTypeConverter::class)
abstract class ChannelsDatabase : RoomDatabase() {
    abstract fun channelDao(): ChannelDao
    abstract fun episodeDao(): EpisodesDao
    abstract fun categoryDao(): CategoryDao
}
