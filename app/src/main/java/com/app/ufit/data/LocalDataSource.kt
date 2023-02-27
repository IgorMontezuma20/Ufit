package com.app.ufit.data

import com.app.ufit.data.database.ExercisesDao
import com.app.ufit.data.database.entities.ExercisesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val exercisesDao: ExercisesDao
){

    fun readExercises(): Flow<List<ExercisesEntity>> {
        return exercisesDao.readExercises()
    }

    suspend fun insertExercises(exercisesEntity: ExercisesEntity) {
        exercisesDao.insertExercises(exercisesEntity)
    }

}