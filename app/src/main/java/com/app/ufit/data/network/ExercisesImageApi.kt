package com.app.ufit.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ExercisesImageApi {

    @Headers(
        "x-RapidAPI-key: 80a6ac3cfbmshb0ad9c5649d853dp180ff9jsnd97019b47d0c"
    )
    @GET("/getImage")
    suspend fun getImage(
        @QueryMap queries: Map<String, String>
    ): Response<String>

}