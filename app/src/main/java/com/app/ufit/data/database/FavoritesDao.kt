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
interface FavoritesDao
{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
       fun insertFavorite(favorite: FavoritesEntity)


    @Query("SELECT EXISTS (SELECT 1 FROM favorites_table WHERE id = :id)")
    fun isFavorite(id: Int): Boolean


    @Query("DELETE FROM favorites_table WHERE id =:id")
     fun deleteFavorite(id:Int)


    @Query("SELECT * FROM favorites_table")
    fun readFavorites(): Flow<List<FavoritesEntity>>

    @Query("SELECT * FROM favorites_table WHERE name = :name")
    fun findFavoriteByName(name: String): FavoritesEntity?




}