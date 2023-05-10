package com.app.ufit.viewmodels.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.app.ufit.data.Repository
import com.app.ufit.provider.UsersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteExerciseViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application){

}