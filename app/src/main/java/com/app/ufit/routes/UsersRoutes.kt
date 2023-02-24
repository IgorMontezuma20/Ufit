package com.app.ufit.routes

import com.app.ufit.models.ResponseHttp
import com.app.ufit.models.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UsersRoutes {

    @POST("users/create")
    fun register(@Body user: User): Call<ResponseHttp>

    @FormUrlEncoded
    @POST("users/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseHttp>

    @Headers(
        "x-RapidAPI-key: 80a6ac3cfbmshb0ad9c5649d853dp180ff9jsnd97019b47d0c"
    )
    @GET("/getImage")
    fun getImage(
        @QueryMap queries: Map<String, String>
    ): Call<ResponseHttp>
}