package com.app.ufit.viewmodels.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.ufit.data.SharedPref
import com.app.ufit.models.User
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    application: Application,
) : AndroidViewModel(application) {

    val success = MutableLiveData<User>()


    fun getUserFromSession() {

        val sharedPref = SharedPref(getApplication())
        val gson = Gson()

        if (!sharedPref.getData("user").isNullOrBlank()) {
            // SI EL USARIO EXISTE EN SESION
            val user = gson.fromJson(sharedPref.getData("user"), User::class.java)
            success.postValue(user)

        }

    }
}