package com.example.mindsvalleyapplication.di

import android.app.Application
import androidx.room.Room
import com.example.mindsvalleyapplication.feature_channels.data.data_source.ApiService
import com.example.mindsvalleyapplication.feature_channels.common.Constants
import com.example.mindsvalleyapplication.feature_channels.data.data_source.local.ChannelsDatabase
import com.example.mindsvalleyapplication.feature_channels.data.repository.ChannelsRepositoryImpl
import com.example.mindsvalleyapplication.feature_channels.domain.repository.ChannelsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =
            HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS) // Connection timeout
            .readTimeout(30, TimeUnit.SECONDS) // Read timeout
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(provideOkHttpClient()) // Use the custom OkHttpClient
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideChannelsDatabase(app: Application): ChannelsDatabase {
        // Provide an in-memory version of ChannelsDatabase for testing
        return Room.inMemoryDatabaseBuilder(
            app,
            ChannelsDatabase::class.java
        ).build()
    }

    @Singleton
    @Provides
    fun channelsRepository(api: ApiService, database: ChannelsDatabase): ChannelsRepository {
        return ChannelsRepositoryImpl(api, database)
    }



}
