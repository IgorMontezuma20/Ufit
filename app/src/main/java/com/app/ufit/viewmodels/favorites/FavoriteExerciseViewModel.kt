package com.app.ufit.viewmodels.favorites

import android.app.Application
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.app.ufit.data.Repository
import com.app.ufit.data.database.ExercisesDatabase
import com.app.ufit.data.database.entities.ExercisesEntity
import com.app.ufit.data.database.entities.FavoritesEntity
import com.app.ufit.models.ExercisesItem
import com.app.ufit.provider.UsersProvider
import com.app.ufit.util.Constants.Companion.DATABASE_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteExerciseViewModel @Inject constructor(
    private val repository: Repository,
    private val usersProvider: UsersProvider,
    application: Application
) : AndroidViewModel(application){


    lateinit var exercisesDatabase: ExercisesDatabase

    private fun insertExercise(exercisesEntity: ExercisesEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertExercises(exercisesEntity)
        }

    suspend fun addFavorite(exercises: List<ExercisesItem>): List<FavoritesEntity> {

        val exercisesEntity = ExercisesEntity(exercises)
        insertExercise(exercisesEntity)

    }

    suspend fun readFavorite(exerciseItem: ExercisesItem): List<FavoritesEntity> {



    }



}