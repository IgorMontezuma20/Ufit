package com.app.ufit.viewmodels

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
import com.app.ufit.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {


    var exercisesResponse: MutableLiveData<NetworkResult<List<ExercisesItem>>> = MutableLiveData()

    fun getExercises(queries: Map<String, String>) = viewModelScope.launch {

        getExercisesSafeCall(queries)

    }

    private suspend fun getExercisesSafeCall(queries: Map<String, String>) {
        exercisesResponse.value = NetworkResult.Loading()
        if(hasInternetConnection()){

            Log.d("Retorno api", repository.remote.getExercises(queries).toString())
            try {
                val response = repository.remote.getExercises(queries)

                exercisesResponse.value = handleExercisesResponse(response)
            } catch (e: Exception){
                exercisesResponse.value = NetworkResult.Error(e.toString())
            }

        }else{
            exercisesResponse.value = NetworkResult.Error("No Internet Connection.")
        }

    }

    private fun handleExercisesResponse(response: Response<List<ExercisesItem>>): NetworkResult<List<ExercisesItem>>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }
//            response.body()!!.results.isNullOrEmpty() -> {
//                return NetworkResult.Error("Recipes not found.")
//            }
            response.isSuccessful -> {
                val exercises = response.body()
                return NetworkResult.Success(exercises!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
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