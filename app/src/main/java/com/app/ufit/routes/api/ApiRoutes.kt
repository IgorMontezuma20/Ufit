package com.app.ufit.routes.api

import com.app.ufit.routes.UsersRoutes
import com.app.ufit.util.Constants.Companion.IMAGE_BASE_URL


class ApiRoutes {


    val API_URL = "http://192.168.0.106:3000/api/"
    val IMAGE_API = IMAGE_BASE_URL
    val retrofit = RetrofitClient()

    fun getUsersRouters(): UsersRoutes {
        return retrofit.getClient(API_URL).create(UsersRoutes::class.java)
    }

    fun getImagesRoutes(): UsersRoutes{
        return retrofit.getClient(IMAGE_API).create(UsersRoutes::class.java)    }
}

