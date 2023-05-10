package com.app.ufit.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.app.ufit.data.database.entities.ExercisesEntity
import com.app.ufit.data.database.entities.FavoritesEntity
import kotlinx.coroutines.flow.Flow

@Dao

interface ExercisesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercises(exercisesEntity: ExercisesEntity)

    @Query("SELECT  * FROM exercises_table ORDER BY id ASC")
    fun readExercises(): Flow<List<ExercisesEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateExercises(exercises: List<ExercisesEntity>)




}