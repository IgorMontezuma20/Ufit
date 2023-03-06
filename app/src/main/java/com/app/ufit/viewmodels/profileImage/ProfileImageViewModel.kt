package com.app.ufit.viewmodels.profileImage

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import com.app.ufit.data.SharedPref
import com.app.ufit.models.ResponseHttp
import com.app.ufit.models.User
import com.app.ufit.provider.UsersProvider
import com.github.dhaval2404.imagepicker.ImagePicker
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

    private var imageFile: File? = null
    var sharedPref: SharedPref? = null
    var user: User? = null



    fun saveImage() {

        if (imageFile != null && user != null) {
            usersProvider.update(imageFile!!, user!!)?.enqueue(object : Callback<ResponseHttp> {
                override fun onResponse(
                    call: Call<ResponseHttp>,
                    response: Response<ResponseHttp>
                ) {

                    Log.d(TAG, "RESPONSE: $response")
                    Log.d(TAG, "BODY: ${response.body()}")

                    saveUserInSession(response.body()?.data.toString())

                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG, "Error: ${t.message}")
                    Toast.makeText(getApplication(), "Error: ${t.message}", Toast.LENGTH_LONG)
                        .show()
                }

            })
        } else {
            Toast.makeText(
                getApplication(),
                "La imagen no puede ser nula ni tampoco los datos de sesion del usuario",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun saveUserInSession(data: String) {
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)
        sharedPref?.save("user", user)

    }


    fun getUserFromSession() {

        val gson = Gson()


        if (!sharedPref?.getData("user").isNullOrBlank()) {
            // SI EL USARIO EXISTE EN SESION
             user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
        }


    }


}