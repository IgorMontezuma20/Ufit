package com.app.ufit.data

import com.app.ufit.data.network.ExercisesApi
import com.app.ufit.data.network.ExercisesImageApi
import com.app.ufit.models.ExercisesItem
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val exercisesApi: ExercisesApi,
    private val exercisesImageApi: ExercisesImageApi
) {

    suspend fun getExercises(queries: Map<String, String>): Response<List<ExercisesItem>>{
       return exercisesApi.getExercises(queries)
    }

    suspend fun exercisesImageApi(queries: Map<String, String>): Response<String>{
        return exercisesImageApi.getImage(queries)
    }

}