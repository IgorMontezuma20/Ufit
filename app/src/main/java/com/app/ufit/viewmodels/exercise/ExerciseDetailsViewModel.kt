package com.app.ufit.viewmodels.exercise

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.ufit.provider.UsersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ExerciseDetailsViewModel @Inject constructor(
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
}