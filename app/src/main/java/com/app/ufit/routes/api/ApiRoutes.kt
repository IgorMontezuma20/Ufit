package com.app.ufit.routes.api

import com.app.ufit.routes.UsersRoutes


class ApiRoutes {


    val API_URL = "http://192.168.0.106:3000/api/"
    val retrofit = RetrofitClient()

    fun getUsersRouters(): UsersRoutes {
        return retrofit.getClient(API_URL).create(UsersRoutes::class.java)
    }
}

