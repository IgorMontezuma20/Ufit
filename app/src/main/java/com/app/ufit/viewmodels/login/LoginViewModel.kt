package com.app.ufit.viewmodels.login

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.ufit.data.SharedPref
import com.app.ufit.models.ResponseHttp
import com.app.ufit.models.User
import com.app.ufit.provider.UsersProvider
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val usersProvider: UsersProvider,
    application: Application
) : AndroidViewModel(application) {


    val success = MutableLiveData<Boolean>()
    val load = MutableLiveData<Boolean>()

    fun loginUser(email: String, password: String) {
        load.postValue(true)
        usersProvider.login(email, password)?.enqueue(object : Callback<ResponseHttp> {
            override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {

                Log.d("Main", "Response : ${response.body()}")

                if (response.body()?.isSuccess == true) {
                    Toast.makeText(getApplication(), response.body()?.message, Toast.LENGTH_LONG)
                        .show()
                    saveUserInSession(response.body()?.data.toString())
                    success.postValue(true)
                    load.postValue(false)

                } else {
                    Toast.makeText(
                        getApplication(),
                        "Os dados não estão corretos ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                load.postValue(false)
                Log.d("Main", "Houve um Erro ${t.message}")
                Toast.makeText(getApplication(), "Houve um Erro ${t.message}", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    fun saveUserInSession(data: String) {
        val sharedPref = SharedPref(getApplication())
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)
        sharedPref.save("user", user)
    }

    fun getUserFromSession() {
        val sharedPref = SharedPref(getApplication())

        if (!sharedPref.getData("user").isNullOrBlank()) {
            success.postValue(true)
        }
    }

    fun verifyLoggedIn(): Boolean {
        val sharedPref = SharedPref(getApplication())

        if (!sharedPref.getData("user").isNullOrBlank()) {
            return true
        }

        return false
    }
}