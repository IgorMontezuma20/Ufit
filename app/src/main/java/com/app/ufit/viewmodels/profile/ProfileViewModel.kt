package com.app.ufit.viewmodels.profile

import android.app.Application
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.ufit.data.SharedPref
import com.app.ufit.models.User
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    application: Application,
) : AndroidViewModel(application) {

    val success = MutableLiveData<User>()
    var userBirthDate = Date()


    fun getUserFromSession() {

        val sharedPref = SharedPref(getApplication())
        val gson = Gson()

        if (!sharedPref.getData("user").isNullOrBlank()) {
            // SI EL USARIO EXISTE EN SESION
            val user = gson.fromJson(sharedPref.getData("user"), User::class.java)
            userBirthDate = user.birthDate
            success.postValue(user)

        }

    }

    fun getUserAge(): String {
        val currentDate = Date()
        val diff: Long = currentDate.getTime() - userBirthDate.getTime()
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val months = days / 31
        val years = months / 12

        return years.toString()
    }

    fun logout() {
        val sharedPref = SharedPref(getApplication())

        sharedPref.remove("user")

    }
}