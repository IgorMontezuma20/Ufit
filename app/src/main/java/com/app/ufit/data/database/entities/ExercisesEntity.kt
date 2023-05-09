package com.app.ufit.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.ufit.models.Exercises
import com.app.ufit.models.ExercisesItem
import com.app.ufit.util.Constants.Companion.EXERCISES_TABLE

@Entity(tableName = EXERCISES_TABLE)
class ExercisesEntity(
    var ExercisesItem: List<ExercisesItem>,


) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

}