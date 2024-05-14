package com.example.mindsvalleyapplication.feature_channels.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoriesResponseModel>)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<CategoriesResponseModel>>
}