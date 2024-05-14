package com.example.mindsvalleyapplication.feature_channels.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cover_asset")
data class CoverAsset(
    @PrimaryKey (autoGenerate = true) val id: Int =0,
    val url: String
)