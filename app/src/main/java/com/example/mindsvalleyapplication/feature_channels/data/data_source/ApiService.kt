package com.example.mindsvalleyapplication.feature_channels.data.data_source

import okhttp3.ResponseBody
import retrofit2.http.GET

interface ApiService {

    @GET("Xt12uVhM")
    suspend fun getChannels(): ResponseBody


    @GET("A0CgArX3")
    suspend fun getCategories(): ResponseBody


    @GET("z5AExTtw")
    suspend fun getNewEpisodes(): ResponseBody


}