package com.example.mindsvalleyapplication.feature_channels.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.model.EpisodesResponseModel

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: CategoriesResponseModel)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): CategoriesResponseModel?

}