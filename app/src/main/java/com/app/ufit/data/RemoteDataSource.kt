package com.app.ufit.data

import com.app.ufit.data.network.ExercisesApi
import com.app.ufit.models.Exercises
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val exercisesApi: ExercisesApi
) {

    suspend fun getExercises(queries: Map<String, String>): Response<Exercises>{
       return exercisesApi.getExercises(queries)
    }

}