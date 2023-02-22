package com.app.ufit.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import com.app.ufit.models.User
import com.app.ufit.provider.UsersProvider
import com.google.gson.Gson
import dagger.hilt.android.internal.Contexts.getApplication
import javax.inject.Inject


class SharedPref @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private var prefs: SharedPreferences? = null

    init {
        //prefs = fragment.sharedPreferences("com.app.ufit", Context.MODE_PRIVATE)
        prefs =
            getApplication(application).getSharedPreferences("com.app.ufit", Context.MODE_PRIVATE)
    }


    fun save(key: String, objeto: Any) {

        try {

            val gson = Gson()
            val json = gson.toJson(objeto)
            with(prefs?.edit()) {
                this?.putString(key, json)
                this?.commit()
            }

        } catch (e: Exception) {
            Log.d("ERROR", "Err: ${e.message}")
        }

    }

    fun getData(key: String): String? {
        val data = prefs?.getString(key, "")
        return data
    }





}

