package com.app.ufit.viewmodels.register

import android.app.Application
import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.ufit.models.ResponseHttp
import com.app.ufit.models.User
import com.app.ufit.provider.UsersProvider
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterInfoViewModel  @Inject constructor(
    //private val user: User,
    private val usersProvider: UsersProvider,
    application: Application
) : AndroidViewModel(application) {

    val success = MutableLiveData<Boolean>()
    val load = MutableLiveData<Boolean>()

    fun registerUser(user: User){
        load.postValue(true)
        usersProvider.register(user)?.enqueue(object : Callback<ResponseHttp> {
            override fun onResponse(
                call: Call<ResponseHttp>,
                response: Response<ResponseHttp>
            ) {
                success.postValue(true)
                load.postValue(false)

                Toast.makeText(
                    getApplication(),
//                    response.body()?.message,
                    "Ocorreu um erro",
                    Toast.LENGTH_LONG
                ).show()
                Log.d(ContentValues.TAG, "Response: ${response}")
                Log.d(ContentValues.TAG, "Body: ${response.body()}")
            }

            override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                load.postValue(false)
                Log.d(ContentValues.TAG, "Ocorreu um error ${t.message}")
                Toast.makeText(
                    getApplication(),
                    "Ocorreu um error ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
            }


        })

    }


}