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
        "x-RapidAPI-key: c023a22db5msh626f4fd1fc55938p1a2579jsnb91b6c4c1733"
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

    @PUT("users/updateWithoutImage")
    fun updateWithoutImage(
        @Body user: User
    ): Call<ResponseHttp>

    @DELETE("users/delete/{userId}")
    fun deleteUser(@Path("userId") userId: String): Call<ResponseHttp>
}