package com.example.mindsvalleyapplication.feature_channels.data.data_source

import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.ChannelsResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.EpisodesResponseModel
import okhttp3.ResponseBody
import retrofit2.http.GET

interface ApiService {

    @GET("Xt12uVhM")
    suspend fun getChannels(): ChannelsResponseModel


    @GET("A0CgArX3")
    suspend fun getCategories(): CategoriesResponseModel


    @GET("z5AExTtw")
    suspend fun getNewEpisodes(): EpisodesResponseModel


}