package com.app.ufit.data.network

import com.app.ufit.models.Exercises
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ExercisesApi {

    @GET("/exercises")
    suspend fun getExercises(
        @QueryMap queries: Map<String, String>
    ): Response<Exercises>

}