package com.app.ufit.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.ufit.models.ExercisesItem
import com.app.ufit.util.Constants.Companion.FAVORITE_EXERCISES_TABLE
import com.google.gson.annotations.SerializedName


@Entity(tableName = FAVORITE_EXERCISES_TABLE)
class FavoritesEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    @SerializedName("difficulty")
    val difficulty: String,
//    @SerializedName("equipment")
//    val equipment: String,
    @SerializedName("equipment")
    val equipment: String,
    @SerializedName("instructions")
    val instructions: String,
    @SerializedName("muscle")
    val muscle: String,
    @SerializedName("name")
    val name: String?=null,
    @SerializedName("type")
    val type: String

//    var id: Int,
//    //var exercisesItem: ExercisesItem

)