package com.example.mindsvalleyapplication.feature_channels.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.EpisodesResponseModel
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(episodes:EpisodesResponseModel)

    @Query("SELECT * FROM episodes")
    fun getAllEpisodes(): EpisodesResponseModel?
}