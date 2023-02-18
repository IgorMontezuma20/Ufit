package com.app.ufit.viewmodels.register

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import com.app.ufit.models.ResponseHttp
import com.app.ufit.models.User
import com.app.ufit.provider.UsersProvider
import com.app.ufit.ui.fragments.register.RegisterFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    //private val user: User,
    private val usersProvider: UsersProvider,
    application: Application
) : AndroidViewModel(application) {



    fun registerUser(user: User){

        usersProvider.register(user)?.enqueue(object : Callback<ResponseHttp> {
            override fun onResponse(
                call: Call<ResponseHttp>,
                response: Response<ResponseHttp>
            ) {
                Toast.makeText(
                    getApplication(),
                    response.body()?.message,
                    Toast.LENGTH_LONG
                ).show()
                Log.d(TAG, "Response: ${response}")
                Log.d(TAG, "Body: ${response.body()}")
            }

            override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                Log.d(TAG, "Ocorreu um error ${t.message}")
                Toast.makeText(
                    getApplication(),
                    "Ocorreu um error ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
            }


        })

    }




}