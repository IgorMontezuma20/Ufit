package com.app.ufit.provider

import com.app.ufit.models.ResponseHttp
import com.app.ufit.models.User
import com.app.ufit.routes.UsersRoutes
import com.app.ufit.routes.api.ApiRoutes
import retrofit2.Call

class UsersProvider {

    private var usersRoutes : UsersRoutes? = null

    init{
        val api = ApiRoutes()
        usersRoutes = api.getUsersRouters()

    }

    fun register(user: User): Call<ResponseHttp>?{

        return  usersRoutes?.register(user)
    }
}