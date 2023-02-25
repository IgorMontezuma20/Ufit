package com.app.ufit.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.ufit.models.ExercisesItem
import com.app.ufit.util.Constants.Companion.FAVORITE_EXERCISES_TABLE


@Entity(tableName = FAVORITE_EXERCISES_TABLE)
class FavoritesEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var exercisesItem: ExercisesItem

)