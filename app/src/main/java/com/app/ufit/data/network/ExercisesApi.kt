package com.app.ufit.data.network

import com.app.ufit.models.ExercisesItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface ExercisesApi {

    @Headers(
        "x-api-key: dsfa4VaUvTJFkUMxolR4W2gbROKy2xkYSdOyA6wx"
    )
    @GET("/v1/exercises")
    suspend fun getExercises(
       @QueryMap queries: Map<String, String>

    ): Response<List<ExercisesItem>>

}