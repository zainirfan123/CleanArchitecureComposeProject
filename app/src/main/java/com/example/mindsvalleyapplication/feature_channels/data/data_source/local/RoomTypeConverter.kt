package com.example.mindsvalleyapplication.feature_channels.data.data_source.local

import androidx.room.TypeConverter
import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.ChannelsResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.EpisodesResponseModel
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
    fun fromEpisodeData(data: EpisodesResponseModel.Data?): String? {
        return gson.toJson(data)
    }

    @TypeConverter
    fun toEpisodeData(dataString: String?): EpisodesResponseModel.Data? {
        return gson.fromJson(dataString, EpisodesResponseModel.Data::class.java)
    }

    @TypeConverter
    fun fromCategoryData(data: CategoriesResponseModel.Data?): String? {
        return gson.toJson(data)
    }

    @TypeConverter
    fun toCategoryData(dataString: String?): CategoriesResponseModel.Data? {
        return gson.fromJson(dataString, CategoriesResponseModel.Data::class.java)
    }
}