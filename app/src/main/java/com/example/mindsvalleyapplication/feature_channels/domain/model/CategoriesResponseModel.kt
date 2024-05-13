package com.example.mindsvalleyapplication.feature_channels.domain.model

data class CategoriesResponseModel(val data: Data) {
  data class Data(val categories: List<Category>) {
    data class Category(val name: String)
  }
}
