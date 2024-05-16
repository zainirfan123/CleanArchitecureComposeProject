package com.example.mindsvalleyapplication.feature_channels.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mindsvalleyapplication.feature_channels.data.data_source.local.RoomTypeConverter

@Entity(tableName = "categories")
data class CategoriesResponseModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @TypeConverters(RoomTypeConverter::class) val data: Data
) {
    data class Data(val categories: List<Category>? = emptyList()) {
        data class Category(val name: String = "")
    }
}
