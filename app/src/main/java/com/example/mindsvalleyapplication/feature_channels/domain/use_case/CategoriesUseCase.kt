package com.example.mindsvalleyapplication.feature_channels.domain.use_case

import com.example.mindsvalleyapplication.feature_channels.common.Resource
import com.example.mindsvalleyapplication.feature_channels.domain.model.CategoriesResponseModel
import com.example.mindsvalleyapplication.feature_channels.domain.repository.ChannelsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(private val repository: ChannelsRepository) {
    operator fun invoke(isFetchFromDatabase: Boolean): Flow<Resource<CategoriesResponseModel>>  = flow {
        try {
            emit(Resource.Loading())
            val categoriesResponse = repository.getCategories(isFetchFromDatabase)
            if (categoriesResponse.data.categories.isNullOrEmpty()){
                emit(Resource.Error("No data found!"))
                return@flow
            }
            emit(Resource.Success(categoriesResponse))
        }catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage?:" An Unexpected error occurred."))
        }catch (e:IOException){
            emit(Resource.Error(e.localizedMessage?:"Couldn't reach server. Check your internet connection"))
        }
    }
}