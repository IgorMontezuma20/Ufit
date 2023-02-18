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
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class UsersProvider @Inject constructor() {

    private var usersRoutes : UsersRoutes? = null

    init{
        val api = ApiRoutes()
        usersRoutes = api.getUsersRouters()

    }


    @Provides
    fun register(user: User): Call<ResponseHttp>?{

        return  usersRoutes?.register(user)
    }
}