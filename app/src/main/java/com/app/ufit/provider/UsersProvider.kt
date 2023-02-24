package com.app.ufit.provider

import com.app.ufit.models.ResponseHttp
import com.app.ufit.models.User
import com.app.ufit.routes.UsersRoutes
import com.app.ufit.routes.api.ApiRoutes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class UsersProvider @Inject constructor() {

    private var usersRoutes : UsersRoutes? = null
    private var imageRoutes: UsersRoutes? = null

    init{
        val api = ApiRoutes()
        usersRoutes = api.getUsersRouters()
        imageRoutes = api.getImagesRoutes()

    }



    fun register(user: User): Call<ResponseHttp>?{

        return  usersRoutes?.register(user)
    }

    fun login(email:String,password:String):Call<ResponseHttp>?{

        return  usersRoutes?.login(email, password)
    }

    suspend fun exercisesImageApi(queries: Map<String, String>): Call<ResponseHttp>? {
        return imageRoutes?.getImage(queries)
    }

}