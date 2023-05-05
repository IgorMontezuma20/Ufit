package com.app.ufit.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.app.ufit.data.database.entities.FavoritesEntity

@Dao
interface FavoriteExercisesDao {

    @Query("SELECT * FROM FAVORITE_EXERCISES_TABLE")
    suspend fun getAll(): List<FavoritesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(favoriteExercise: List<FavoritesEntity>)

    @Update
    suspend fun update(favoriteExercise: FavoritesEntity)
}
