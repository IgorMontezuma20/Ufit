package com.app.ufit.routes

import com.app.ufit.models.ResponseHttp
import com.app.ufit.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersRoutes {

    @POST("users/create")
    fun register(@Body user: User): Call<ResponseHttp>
}