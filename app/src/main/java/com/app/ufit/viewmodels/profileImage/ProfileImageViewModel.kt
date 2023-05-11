package com.app.ufit.viewmodels.profileImage

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileImageViewModel @Inject constructor(
    application: Application,
    private val usersProvider: UsersProvider,


    ) : AndroidViewModel(application) {

    private val _showSnackbarEvent = MutableLiveData<String>()
    val showSnackbarEvent: LiveData<String> get() = _showSnackbarEvent

    fun saveImage(imageFile: File?) {
        val user = getUserFromSession()

        if (imageFile != null && user != null) {
            usersProvider.update(imageFile!!, user!!)?.enqueue(object : Callback<ResponseHttp> {
                override fun onResponse(
                    call: Call<ResponseHttp>,
                    response: Response<ResponseHttp>
                ) {
                    Log.d(TAG, "RESPONSE: $response")
                    Log.d(TAG, "BODY: ${response.body()}")
                    saveUserInSession(response.body()?.data.toString())
                    _showSnackbarEvent.value = "Todos os dados foram salvos"
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG, "Error: ${t.message}")
                    _showSnackbarEvent.value = "Error: ${t.message}"
                }

            })
        }
    }


    fun updateData(imageFile: File?, name: String, lastName: String) {
        val user = getUserFromSession()

        user.name = name
        user.lastName = lastName

        if (imageFile != null) {
            usersProvider.update(imageFile!!, user!!)?.enqueue(object : Callback<ResponseHttp> {
                override fun onResponse(
                    call: Call<ResponseHttp>,
                    response: Response<ResponseHttp>
                ) {
                    Log.d(TAG, "RESPONSE: $response")
                    Log.d(TAG, "BODY: ${response.body()}")

                    _showSnackbarEvent.value =  response.body()?.message

                    saveUserInSession(response.body()?.data.toString())
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG, "Error: ${t.message}")
                    _showSnackbarEvent.value = "Error: ${t.message}"
                }
            })
        } else {
            usersProvider.updateWithoutImage(user!!)?.enqueue(object : Callback<ResponseHttp> {
                override fun onResponse(
                    call: Call<ResponseHttp>,
                    response: Response<ResponseHttp>
                ) {
                    Log.d(TAG, "RESPONSE: $response")
                    Log.d(TAG, "BODY: ${response.body()}")

                    _showSnackbarEvent.value = response.body()?.message
                    saveUserInSession(response.body()?.data.toString())
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG, "Error: ${t.message}")
                    Toast.makeText(getApplication(), "Error: ${t.message}", Toast.LENGTH_LONG)
                        .show()
                }
            })
        }
    }

    fun saveUserInSession(data: String) {
        val sharedPref = SharedPref(getApplication())
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)
        sharedPref?.save("user", user)
    }


    fun getUserFromSession(): User {
        val sharedPref = SharedPref(getApplication())
        val gson = Gson()
        var user: User? = null
        if (!sharedPref.getData("user").isNullOrBlank()) {
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
        }

        return user!!
    }


}