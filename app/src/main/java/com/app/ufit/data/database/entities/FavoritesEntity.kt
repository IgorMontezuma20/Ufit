package com.app.ufit.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.ufit.models.ExercisesItem
import com.app.ufit.util.Constants.Companion.FAVORITE_EXERCISES_TABLE
import com.google.gson.annotations.SerializedName


@Entity(tableName = "favorites_table")
data class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val difficulty: String,
    val equipment: String,
    val instructions: String,
    val muscle: String,
    val name: String,
    val type: String

)