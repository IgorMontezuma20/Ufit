package com.app.ufit.viewmodels.exercise

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.ufit.data.Repository
import com.app.ufit.models.ExercisesItem
import com.app.ufit.provider.UsersProvider
import com.app.ufit.util.NetworkResult
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class ExerciseDetailsViewModel @Inject constructor(
    private val repository: Repository,
    private val usersProvider: UsersProvider,
    application: Application
) : AndroidViewModel(application) {

    var imageResponse: MutableLiveData<NetworkResult<String>> = MutableLiveData()




    fun getImage() {
        usersProvider.exercisesImageApi()

    }




    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}