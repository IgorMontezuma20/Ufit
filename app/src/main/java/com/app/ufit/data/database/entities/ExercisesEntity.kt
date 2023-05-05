package com.app.ufit.data.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.ufit.models.Exercises
import com.app.ufit.models.ExercisesItem
import com.app.ufit.util.Constants.Companion.EXERCISES_TABLE
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//@Entity(tableName = EXERCISES_TABLE)

@Parcelize
 data class ExercisesEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,
    @SerializedName("difficulty")
    val difficulty: String,
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
) : Parcelable



