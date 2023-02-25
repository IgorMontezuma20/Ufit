package com.app.ufit.viewmodels.exercise

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.ufit.data.Repository
import com.app.ufit.models.ExercisesItem
import com.app.ufit.models.ResponseHttp
import com.app.ufit.provider.UsersProvider
import com.app.ufit.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ExerciseDetailsViewModel @Inject constructor(
    private val repository: Repository,
    private val usersProvider: UsersProvider,
    application: Application
) : AndroidViewModel(application) {

    var imageResponse: MutableLiveData<Bitmap> = MutableLiveData()


    fun getImage(muscleGroups: String) {

        usersProvider.exercisesImageApi(muscleGroups)?.enqueue(object : Callback<ResponseBody> {

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                imageResponse.postValue(BitmapFactory.decodeStream(response.body()?.byteStream()))
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    getApplication(),
                    t.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }

        })


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