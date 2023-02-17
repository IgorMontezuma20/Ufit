package com.app.ufit.viewmodels.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.app.ufit.models.User
import com.app.ufit.provider.UsersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val user: User,
    application: Application
) : AndroidViewModel(application) {


    var usersProvider = UsersProvider()

    fun registerUser(user: User){

    }

}