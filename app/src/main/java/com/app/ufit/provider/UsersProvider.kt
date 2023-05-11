package com.app.ufit.provider

import com.app.ufit.models.ResponseHttp
import com.app.ufit.models.User
import com.app.ufit.routes.UsersRoutes
import com.app.ufit.routes.api.ApiRoutes
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import java.io.File
import javax.inject.Inject


class UsersProvider @Inject constructor() {

    private var usersRoutes: UsersRoutes? = null
    private var imageRoutes: UsersRoutes? = null

    init {
        val api = ApiRoutes()
        usersRoutes = api.getUsersRouters()
        imageRoutes = api.getImagesRoutes()

    }


    fun register(user: User): Call<ResponseHttp>? {

        return usersRoutes?.register(user)
    }

    fun login(email: String, password: String): Call<ResponseHttp>? {

        return usersRoutes?.login(email, password)
    }

    fun exercisesImageApi(muscleGroups: String): Call<ResponseBody>? {
        return imageRoutes?.getImage(muscleGroups)
    }

    fun updateWithoutImage(user: User): Call<ResponseHttp>? {
        return usersRoutes?.updateWithoutImage(user)
    }

    fun update(file: File, user: User): Call<ResponseHttp>? {
        val reqFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        val image = MultipartBody.Part.createFormData("image", file.name, reqFile)
        val requestBody = user.toJson().toRequestBody("text/plain".toMediaTypeOrNull())
        return usersRoutes?.update(image, requestBody)
    }

    fun deleteUser(userId: String): Call<ResponseHttp>? {
        return usersRoutes?.deleteUser(userId)
    }

}