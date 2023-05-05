package com.app.ufit.models


import android.os.Parcelable
import androidx.room.Entity
import com.app.ufit.util.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = Constants.EXERCISES_TABLE)
@Parcelize
data class ExercisesItem(
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
) : Parcelable
