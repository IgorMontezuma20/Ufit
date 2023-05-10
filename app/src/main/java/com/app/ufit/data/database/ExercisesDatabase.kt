package com.app.ufit.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.ufit.data.database.entities.ExercisesEntity
import com.app.ufit.data.database.entities.FavoritesEntity
import com.app.ufit.util.ExercisesTypeConverter

@Database(
    entities = [ExercisesEntity::class, FavoritesEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ExercisesTypeConverter::class)
abstract class ExercisesDatabase: RoomDatabase() {

    abstract fun exercisesDao(): ExercisesDao
    abstract fun favoritesDao(): FavoritesDao

}

