package com.app.ufit.routes

import com.app.ufit.models.ResponseHttp
import com.app.ufit.models.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
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
    //Exercise image
    @GET("/getImage")
    fun getImage(
        @Query("muscleGroups") muscleGroups: String
    ): Call<ResponseBody>

    @Multipart
    @PUT("users/update")
    fun update(
        @Part image: MultipartBody.Part,
        @Part("user") user: RequestBody
    ): Call<ResponseHttp>
}