package com.example.mindsvalleyapplication.feature_channels.data.data_source

import androidx.room.TypeConverter
import com.example.mindsvalleyapplication.feature_channels.domain.model.ChannelsResponseModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromData(data: ChannelsResponseModel.Data?): String? {
        return gson.toJson(data)
    }

    @TypeConverter
    fun toData(dataString: String?): ChannelsResponseModel.Data? {
        return gson.fromJson(dataString, ChannelsResponseModel.Data::class.java)
    }

    @TypeConverter
    fun fromChannelList(channels: List<ChannelsResponseModel.Data.Channel>?): String? {
        return gson.toJson(channels)
    }

    @TypeConverter
    fun toChannelList(channelsString: String?): List<ChannelsResponseModel.Data.Channel>? {
        val listType = object : TypeToken<List<ChannelsResponseModel.Data.Channel>>() {}.type
        return gson.fromJson(channelsString, listType)
    }

    @TypeConverter
    fun fromLatestMediaList(mediaList: List<ChannelsResponseModel.Data.Channel.LatestMedia>?): String? {
        return gson.toJson(mediaList)
    }

    @TypeConverter
    fun toLatestMediaList(mediaListString: String?): List<ChannelsResponseModel.Data.Channel.LatestMedia>? {
        val listType = object : TypeToken<List<ChannelsResponseModel.Data.Channel.LatestMedia>>() {}.type
        return gson.fromJson(mediaListString, listType)
    }

    @TypeConverter
    fun fromSeriesList(seriesList: List<ChannelsResponseModel.Data.Channel.Series>?): String? {
        return gson.toJson(seriesList)
    }

    @TypeConverter
    fun toSeriesList(seriesListString: String?): List<ChannelsResponseModel.Data.Channel.Series>? {
        val listType = object : TypeToken<List<ChannelsResponseModel.Data.Channel.Series>>() {}.type
        return gson.fromJson(seriesListString, listType)
    }

    @TypeConverter
    fun fromCoverAsset(coverAsset: ChannelsResponseModel.Data.Channel.CoverAsset?): String? {
        return gson.toJson(coverAsset)
    }

    @TypeConverter
    fun toCoverAsset(coverAssetString: String?): ChannelsResponseModel.Data.Channel.CoverAsset? {
        return gson.fromJson(coverAssetString, ChannelsResponseModel.Data.Channel.CoverAsset::class.java)
    }

    @TypeConverter
    fun fromIconAsset(iconAsset: ChannelsResponseModel.Data.Channel.IconAsset?): String? {
        return gson.toJson(iconAsset)
    }

    @TypeConverter
    fun toIconAsset(iconAssetString: String?): ChannelsResponseModel.Data.Channel.IconAsset? {
        return gson.fromJson(iconAssetString, ChannelsResponseModel.Data.Channel.IconAsset::class.java)
    }
}