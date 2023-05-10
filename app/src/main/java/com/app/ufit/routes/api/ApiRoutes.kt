package com.app.ufit.routes.api

import com.app.ufit.routes.UsersRoutes
import com.app.ufit.util.Constants.Companion.IMAGE_BASE_URL


class ApiRoutes {


  val API_URL = "https://ufit-node-server-git-main-igormontezuma20.vercel.app/api/"
// val API_URL = "http://10.0.0.101:3000/api/"
    val IMAGE_API = IMAGE_BASE_URL
    val retrofit = RetrofitClient()

    fun getUsersRouters(): UsersRoutes {
        return retrofit.getClient(API_URL).create(UsersRoutes::class.java)
    }

    fun getImagesRoutes(): UsersRoutes{
        return retrofit.getClient(IMAGE_API).create(UsersRoutes::class.java)    }
}

