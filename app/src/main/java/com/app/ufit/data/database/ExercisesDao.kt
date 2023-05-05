package com.app.ufit.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.ufit.data.database.entities.ExercisesEntity
import com.app.ufit.data.database.entities.FavoritesEntity
import kotlinx.coroutines.flow.Flow

@Dao

interface ExercisesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercises(exercisesEntity: ExercisesEntity)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertFavoriteExercise(favoritesEntity: FavoritesEntity)

    @Query("SELECT  * FROM exercises_table ORDER BY id ASC")
    fun readExercises(): Flow<List<ExercisesEntity>>

    @Query("DELETE FROM exercises_table WHERE id=:id")
    fun deleteExercise(id: Int)

//    @Query("SELECT * FROM favorite_exercises_table ORDER BY id ASC")
//    fun readFavoriteExercises(): Flow<List<FavoritesEntity>>
//
//    @Delete
//    suspend fun deleteFavoriteExercise(favoritesEntity: FavoritesEntity)
//
//    @Query("DELETE FROM favorite_exercises_table")
//    suspend fun deleteAllFavoriteExercises()



}